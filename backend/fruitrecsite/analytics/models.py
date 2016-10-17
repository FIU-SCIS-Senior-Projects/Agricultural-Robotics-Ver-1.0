from django.db import models
#TODO add human-readable 'verbose' names as the first positional argument of each field (all lowercase)
# is it better to make every model field an array of the data type be default,
# or to make a super-model with an array of models that have singleton model fields

#######################################################################
#                            Mission Model                            #
#######################################################################

class Mission(models.Model):
    pass

#######################################################################
#                      VideoStreamService Models                      #
#######################################################################

# class VideoStreamData(models.Model):
#     mission = models.ForeignKey(Mission, on_delete=models.CASCADE)
#
# class HDVideoStreamData(models.Model):
#     mission = models.ForeignKey(Mission, on_delete=models.CASCADE)

#######################################################################
#                        NavDataService Models                        #
#######################################################################


# Many NavData models are related to one Mission model
class NavData(models.Model):
    mission = models.ForeignKey(Mission, on_delete=models.CASCADE)

class FlightStatusData(models.Model):
    state = models.IntegerField()
    vision = models.IntegerField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class TimeData(models.Model):
    seconds = models.IntegerField()
    useconds = models.IntegerField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class AcceleroRawData(models.Model):
    raw_accs = models.ArrayField(models.PositiveIntegerField(), size=3)
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class AcceleroPhysData(models.Model):
    phys_accs = models.ArrayField(models.FloatField(), size=3)
    phys_gyros = models.ArrayField(models.FloatField(), size=3)
    alim3V3 = models.IntegerField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class GyroRawData(models.Model):
    raw_gyros = models.ArrayField(models.IntegerField(), size=3)
    raw_gyros_110 = models.ArrayField(models.IntegerField(), size=2)
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class GyroPhysData(models.Model):
    gyro_temp = models.IntegerField()
    phys_gyros = models.ArrayField(models.FloatField(), size=3)
    alim3V3 = models.IntegerField()
    vrefEpson = models.IntegerField()
    vrefIDG = models.IntegerField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class GyroOffsetData(models.Model):
    offset_g = models.ArrayField(models.FloatField(), size=3)
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class MagnetoData():
    m = models.ArrayField(models.IntegerField(), size=3)
    mraw = models.ArrayField(models.FloatField(), size=3)
    mrectified = models.ArrayField(models.FloatField(), size=3)
    m_ = models.ArrayField(models.FloatField(), size=3)
    heading_unwrapped = models.FloatField()
    heading_gyro_unwrapped = models.FloatField()
    heading_fusion_unwrapped = models.FloatField()
    #TODO confirm that a models.BinaryField is appropriate
    #byte calibration_ok = b.get();
    calibration_ok = models.BinaryField()
    state = models.IntegerField()
    radius = models.FloatField()
    error_mean = models.FloatField()
    error_var = models.FloatField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

# Euler angles. but where's psi?
class AttitudeData(models.Model):
    theta = models.FloatField()
    phi = models.FloatField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class AltitudeData(models.Model):
    # 16-bit integer
    altitude_vision = models.IntegerField()
    # 7-digit precision float
    altitude_vz = models.FloatField()
    altitude_ref = models.IntegerField()
    altitude_raw = models.IntegerField()
    obs_accZ = models.FloatField()
    obs_alt = models.FloatField()
    # array of floats
    obs_x = models.ArrayField(models.FloatField(), size=3)
    obs_state = models.IntegerField()
    est_vb = models.ArrayField(models.FloatField(), size=2)
    est_state = models.IntegerField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class GPSData(models.Model):
    # max integer part is 3 digits, so assign 13 fractional digits
    latitude = models.DecimalField(max_digits=16, decimal_places=13)
    longitude = models.DecimalField(max_digits=16, decimal_places=13)
    latitudeZero = models.DecimalField(max_digits=16, decimal_places=13)
    longitudeZero = models.DecimalField(max_digits=16, decimal_places=13)
    latitudeFused = models.DecimalField(max_digits=16, decimal_places=13)
    longitudeFused = models.DecimalField(max_digits=16, decimal_places=13)
    attitudeFused = models.DecimalField(max_digits=16, decimal_places=13)
    # max integer part is 4 digits, so assign 12 fractional digits
    elevation = models.DecimalField(max_digits=16, decimal_places=12)
    # TODO what is hdop? how many digits long are its whole and fractional parts?
    hdop = models.DecimalField(max_digits=16, decimal_places=12)
    dataAvailable = models.IntegerField()
    zeroValidated = models.BooleanField()
    wptValidated = models.BooleanField()
    # TODO haw large can a gpsState value be? The IntegerField is 32-bit
    gpsState = models.IntegerField()
    xTrajectory = models.FloatField()
    yTrajectory = models.FloatField()
    xReference = models.FloatField()
    yReference = models.FloatField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

class BatteryVoltageData(models.Model):
    voltage = models.IntegerField()
    navdata = models.ForeignKey(NavData, on_delete=models.CASCADE)

#### JSONModels

