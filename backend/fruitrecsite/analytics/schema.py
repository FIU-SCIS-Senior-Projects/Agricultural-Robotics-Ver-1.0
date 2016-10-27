from graphene import relay, ObjectType, AbstractType
from graphene_django import DjangoObjectType
from graphene_django.filter.fields import DjangoFilterConnectionField
from fruitrecsite.analytics.models import NavData, GyroRawData, GyroPhysData, GyroOffsetData #, Mission, FlightStatusData, TimeData, AcceleroRawData, AcceleroPhysData,\
    # MagnetoData, AttitudeData, AltitudeData, GPSData, BatteryVoltageData


# class MissionNode(DjangoObjectType):
#     class Meta:
#         model = Mission


class NavDataNode(DjangoObjectType):
    class Meta:
        model = NavData
        # filter_fields = ['name', 'ingredients']
        # filter_order_by = ['name']
        interfaces = (relay.Node,)


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
# class AcceleroRawData(DjangoObjectType):
#     class Meta:
#         model = AcceleroRawData
#
#
# class AcceleroPhysDataNode(DjangoObjectType):
#     class Meta:
#         model = AcceleroPhysData
        

class GyroRawDataNode(DjangoObjectType):
    class Meta:
        model = GyroRawData
        # filter_fields = {
        #     'navdata__name': ['exact']
        # }
        # filter_order_by = ['name', 'navdata__name']
        interfaces = (relay.Node,)


class GyroPhysDataNode(DjangoObjectType):
    class Meta:
        model = GyroPhysData
        # filter_fields = {
        #     'navdata__name': ['exact']
        # }
        # filter_order_by = ['name', 'navdata__name']
        interfaces = (relay.Node,)
        

class GyroOffsetDataNode(DjangoObjectType):
    class Meta:
        model = GyroOffsetData
        # filter_fields = {
        #     'navdata__name': ['exact']
        # }
        # filter_order_by = ['name', 'navdata__name']
        interfaces = (relay.Node,)


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


class Query(ObjectType):
    # category = relay.Node.Field(BatteryVoltageDataNode)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageDataNode)
    #
    nav_data = relay.Node.Field(NavDataNode)
    # all_nav_data = DjangoFilterConnectionField(NavDataNode)
    #
    # category = relay.Node.Field(BatteryVoltageDataNode)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageDataNode)
    #
    # battery_voltage_data = relay.Node.Field(BatteryVoltageDataNode)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageDataNode)
    #
    # category = relay.Node.Field(BatteryVoltageDataNode)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageDataNode)
    #
    # category = relay.Node.Field(BatteryVoltageDataNode)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageDataNode)

    gyro_raw_data = relay.Node.Field(GyroRawDataNode)
    # all_gyro_raw_data = DjangoFilterConnectionField(GyroRawDataNode)

    gyro_phys_data = relay.Node.Field(GyroPhysDataNode)
    # all_gyro_phys_data = DjangoFilterConnectionField(GyroPhysDataNode)

    gyro_offset_data = relay.Node.Field(GyroOffsetDataNode)
    # all_gyro_offset_data = DjangoFilterConnectionField(GyroOffsetDataNode)

    # battery_voltage_data = relay.Node.Field(BatteryVoltageDataNode)
    # all_battery_voltage_data = DjangoFilterConnectionField(BatteryVoltageDataNode)
