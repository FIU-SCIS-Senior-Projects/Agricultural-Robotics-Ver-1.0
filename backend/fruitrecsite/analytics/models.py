from django.db import models
from django.contrib.postgres.fields import ArrayField
# TODO add human-readable 'verbose' names as the first positional argument of each field (all lowercase)

#######################################################################
#                            Mission Model                            #
#######################################################################


class Mission(models.Model):
    name = models.CharField(max_length=200)
    drone_ssid = models.CharField(max_length=200)

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


# Many NavDataSet models are related to one Mission model
class NavDataSet(models.Model):
    mission = models.ForeignKey(Mission, on_delete=models.CASCADE)


class NavData(models.Model):
    nav_data_set = models.ForeignKey(NavDataSet, on_delete=models.CASCADE)
    
    
class FlightStatusData(NavData):
    state = models.IntegerField()
    vision = models.IntegerField()


class TimeData(NavData):
    seconds = models.IntegerField()
    u_seconds = models.IntegerField()


class AcceleroRawData(NavData):
    raw_accs = ArrayField(models.PositiveIntegerField(), size=3)


class AcceleroPhysData(NavData):
    phys_accs = ArrayField(models.FloatField(), size=3)
    phys_gyros = ArrayField(models.FloatField(), size=3)
    alim_3v3 = models.IntegerField()


class GyroRawData(NavData):
    raw_gyros = ArrayField(models.IntegerField(), size=3)
    raw_gyros_110 = ArrayField(models.IntegerField(), size=2)

    def __str__(self):
        return

class GyroPhysData(NavData):
    gyro_temp = models.IntegerField()
    phys_gyros = ArrayField(models.FloatField(), size=3)
    alim_3v3 = models.IntegerField()
    vref_epson = models.IntegerField()
    vref_idg = models.IntegerField()


class GyroOffsetData(NavData):
    offset_g = ArrayField(models.FloatField(), size=3)


class MagnetoData(NavData):
    m = ArrayField(models.IntegerField(), size=3)
    m_raw = ArrayField(models.FloatField(), size=3)
    m_rectified = ArrayField(models.FloatField(), size=3)
    # renamed from m_ because postgres "Field names must not end with an underscore."
    m_underscore = ArrayField(models.FloatField(), size=3)
    heading_unwrapped = models.FloatField()
    heading_gyro_unwrapped = models.FloatField()
    heading_fusion_unwrapped = models.FloatField()
    # TODO confirm that a models.BinaryField is appropriate
    calibration_ok = models.BinaryField() # byte calibration_ok = b.get();
    state = models.IntegerField()
    radius = models.FloatField()
    error_mean = models.FloatField()
    error_var = models.FloatField()


# TODO Ask front-end devs why psi is not included with the Euler angles.
class AttitudeData(NavData):
    theta = models.FloatField()
    phi = models.FloatField()


class AltitudeData(NavData):
    # 16-bit integer
    altitude_vision = models.IntegerField()
    # 7-digit precision float
    altitude_vz = models.FloatField()
    altitude_ref = models.IntegerField()
    altitude_raw = models.IntegerField()
    obs_acc_z = models.FloatField()
    obs_alt = models.FloatField()
    # array of floats
    obs_x = ArrayField(models.FloatField(), size=3)
    obs_state = models.IntegerField()
    est_vb = ArrayField(models.FloatField(), size=2)
    est_state = models.IntegerField()


class GPSData(NavData):
    # max integer part is 3 digits, so assign 13 fractional digits
    latitude = models.DecimalField(max_digits=16, decimal_places=13)
    longitude = models.DecimalField(max_digits=16, decimal_places=13)
    latitude_zero = models.DecimalField(max_digits=16, decimal_places=13)
    longitude_zero = models.DecimalField(max_digits=16, decimal_places=13)
    latitude_fused = models.DecimalField(max_digits=16, decimal_places=13)
    longitude_fused = models.DecimalField(max_digits=16, decimal_places=13)
    attitude_fused = models.DecimalField(max_digits=16, decimal_places=13)
    # max integer part is 4 digits, so assign 12 fractional digits
    elevation = models.DecimalField(max_digits=16, decimal_places=12)
    # TODO what is hdop? how many digits long are its whole and fractional parts?
    hdop = models.DecimalField(max_digits=16, decimal_places=12)
    data_available = models.IntegerField()
    zero_validated = models.BooleanField()
    wpt_validated = models.BooleanField()
    # TODO haw large can a gpsState value be? The IntegerField is 32-bit
    gps_state = models.IntegerField()
    x_trajectory = models.FloatField()
    y_trajectory = models.FloatField()
    x_reference = models.FloatField()
    y_reference = models.FloatField()


class BatteryVoltageData(NavData):
    voltage = models.IntegerField()

# the id will be generated by django automatically
# class User(models.Model):
#     role = models.CharField(max_length=200)
#     first_name = models.CharField(max_length=200)
#     last_name = models.CharField(max_length=200)
#     email = models.CharField(max_length=200)
# JSONModels

