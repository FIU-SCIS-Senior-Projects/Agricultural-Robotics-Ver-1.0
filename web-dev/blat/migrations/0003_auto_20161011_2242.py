# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('blat', '0002_blat_crated_by'),
    ]

    operations = [
        migrations.RenameField(
            model_name='blat',
            old_name='crated_by',
            new_name='created_by',
        ),
    ]
