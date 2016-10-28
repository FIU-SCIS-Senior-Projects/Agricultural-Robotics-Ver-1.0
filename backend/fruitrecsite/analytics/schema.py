import graphene
from graphene import relay, resolve_only_args
from graphene_django import DjangoObjectType
from graphene_django.filter.fields import DjangoFilterConnectionField
from .models import Mission, NavDataSet, GyroRawData, GyroPhysData, GyroOffsetData
#, FlightStatusData, TimeData, AcceleroRawData, AcceleroPhysData, MagnetoData, AttitudeData, AltitudeData, GPSData, BatteryVoltageData
from graphene_django.debug import DjangoDebug

def connection_for_type(_type):
    class Connection(graphene.Connection):
        total_count = graphene.Int()

        class Meta:
            name = _type._meta.name + 'Connection'
            node = _type

        def resolve_total_count(self, args, context, info):
            return self.length

    return Connection


# class MissionNode(DjangoObjectType):
#     class Meta:
#         model = Mission
#
#
# class NavDataSetNode(DjangoObjectType):
#     mission = graphene.Field(MissionNode)
#
#     class Meta:
#         model = NavDataSet
#         interfaces = (relay.Node,)


# class FlightStatusDataNode(DjangoObjectType):
#     class Meta:
#         model = FlightStatusData
#
#
# class TimeDataNode(DjangoObjectType):
#     class Meta:
#         model = TimeData
#
#
# class AcceleroRawDataNode(DjangoObjectType):
#     class Meta:
#         model = AcceleroRawData
#
#
# class AcceleroPhysDataNode(DjangoObjectType):
#     class Meta:
#         model = AcceleroPhysData
        
#
# class GyroRawDataNode(NavData):
#
#
# class GyroPhysDataNode(NavData):

#
#
# class GyroOffsetDataNode(NavData):
#     offset_g = ArrayField(models.FloatField(), size=3)
#
#
class GyroRawDataNode(DjangoObjectType):
    raw_gyros = graphene.List(graphene.Int)
    raw_gyros_110 = graphene.List(graphene.Int)

    @resolve_only_args
    def resolve_raw_gyros(self):
        return self.raw_gyros

    @resolve_only_args
    def resolve_raw_gyros_110(self):
        return self.raw_gyros_110

    class Meta:
        model = GyroRawData
        interfaces = (relay.Node,)


GyroRawData.Connection = connection_for_type(GyroRawDataNode)


# class GyroPhysDataNode(DjangoObjectType):
#     gyro_temp = graphene.Int()
#     phys_gyros = graphene.List()
#     alim_3v3 = graphene.Int()
#     vref_epson = graphene.Int()
#     vref_idg = graphene.Int()
#
#     class Meta:
#         model = GyroPhysData
#         interfaces = (relay.Node,)
#
#
# class GyroOffsetDataNode(DjangoObjectType):
#     offset_g = graphene.List()
#
#     class Meta:
#         model = GyroOffsetData
#         interfaces = (relay.Node,)


# class MagnetoDataNode(DjangoObjectType):
#     class Meta:
#         model = MagnetoData
#
#
# class AttitudeDataNode(DjangoObjectType):
#     class Meta:
#         model = AttitudeData
#
#
# class AltitudeDataNode(DjangoObjectType):
#     class Meta:
#         model = AltitudeData
#
#
# class GPSDataNode(DjangoObjectType):
#     class Meta:
#         model = GPSData
#
#
# class BatteryVoltageDataNode(DjangoObjectType):
#     class Meta:
#         model = BatteryVoltageData


class Query(graphene.ObjectType):
    # category = relay.Node.Field(BatteryVoltageData)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageData)
    #
    # nav_data_set = relay.Node.Field(NavDataSet)
    # all_nav_data = DjangoFilterConnectionField(NavData)
    #
    # category = relay.Node.Field(BatteryVoltageData)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageData)
    #
    # battery_voltage_data = relay.Node.Field(BatteryVoltageData)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageData)
    #
    # category = relay.Node.Field(BatteryVoltageData)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageData)
    #
    # category = relay.Node.Field(BatteryVoltageData)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageData)

    gyro_raw_data = relay.Node.Field(GyroRawDataNode)
    all_gyro_raw_data = DjangoFilterConnectionField(GyroRawDataNode)

    # gyro_phys_data = relay.Node.Field(GyroPhysData)
    # all_gyro_phys_data = DjangoFilterConnectionField(GyroPhysData)

    # gyro_offset_data = relay.Node.Field(GyroOffsetData)
    # all_gyro_offset_data = DjangoFilterConnectionField(GyroOffsetData)

    # battery_voltage_data = relay.Node.Field(BatteryVoltageData)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageData)
    viewer = graphene.Field(lambda: Query)

    debug = graphene.Field(DjangoDebug, name='__debug')

    def resolve_viewer(self, *args, **kwargs):
        return self


class CreateGyroRawData(graphene.ClientIDMutation):

    class Input:
        raw_gyros = graphene.List(graphene.Int)
        raw_gyros_110 = graphene.List(graphene.Int)

    gyro_raw_data = graphene.Field(GyroRawDataNode)
    ok = graphene.Boolean()

    @classmethod
    def mutate_and_get_payload(cls, input, info):
        raw_gyros = input.get('raw_gyros')
        try:
            raw_gyros = [int(g) for g in raw_gyros]
        except ValueError:
            non_ints = [g for g in raw_gyros if g.type != int]
            # TODO specify index at which non-integer was found
            raise Exception("Expected integer values for raw_gyros but found: {}".format(non_ints[0].type))

        raw_gyros_110 = input.get('raw_gyros_110')
        try:
            raw_gyros_110 = [int(g) for g in raw_gyros_110]
        except ValueError:
            non_ints = [g for g in raw_gyros_110 if g.type != int]
            # TODO specify index at which non-integer was found
            raise Exception("Expected integer values for raw_gyros_110 but found: {}".format(non_ints[0].type))

        # schemaField.DjangoModel.newModelWithParametersName=name_Homeworld=homeworls
        gyro_raw_data = GyroRawData._meta.model(raw_gyros=raw_gyros, raw_gyros_110=raw_gyros_110)
        # DjangoModel.PostgresTable.AddRowHero
        gyro_raw_data.save()

        return CreateGyroRawData(gyro_raw_data=gyro_raw_data, ok=bool(gyro_raw_data.id))


class Mutation(graphene.ObjectType):
    create_gyro_raw_data = graphene.Field(CreateGyroRawData)


schema = graphene.Schema(
    query=Query,
    mutation=Mutation,
)