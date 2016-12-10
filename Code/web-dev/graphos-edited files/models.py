from django.db import models


class TimeSeries(models.Model):
    """
    value is used as y axis for the graph
    """
    value = models.IntegerField()


class Account(models.Model):
    week = models.CharField(max_length=2)
    count = models.PositiveIntegerField()
    area = models.PositiveIntegerField()
    farmer = models.CharField(max_length=10)
    created_on = models.DateTimeField(auto_now_add=True)
