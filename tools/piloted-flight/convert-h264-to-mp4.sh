#!/usr/bin/env bash


for f in $(find -name video.h264) 
do
   OUTPUT_DIR=`dirname $f`
   ffmpeg -i $f -vcodec libxvid -b 1200k -acodec libmp3lame -ab 128k $OUTPUT_DIR/video.mp4
done

