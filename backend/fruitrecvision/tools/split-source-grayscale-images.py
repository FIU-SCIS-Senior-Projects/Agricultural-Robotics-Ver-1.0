import os
import cv2
import numpy as np
import argparse as ap

parser = ap.ArgumentParser()

parser.add_argument('-i', '--input-image-path', help='path to the source image folder', required=True)
parser.add_argument('-o', '--output-image-path', help='path to the cropped image folder', required=True)
parser.add_argument('-x', '--output-image-width', help='width of the cropped images', type=int, required=True)
parser.add_argument('-y', '--output-image-height', help='height of the cropped images', type=int, required=True)

args = vars(parser.parse_args())

input_image_path = args['input_image_path']
output_image_path = args['output_image_path']
output_image_width = args['output_image_width']
output_image_height = args['output_image_height']

count = 0

input_image_names = [name for name in os.listdir(input_image_path) if name.endswith('.jpg') or name.endswith('.jpeg')]

for image_name in input_image_names:
    input_image = cv2.imread(input_image_path + "/" + image_name, cv2.IMREAD_GRAYSCALE)

    img_shape = np.shape(input_image)
    print (img_shape)

    y_range = range(img_shape[0])
    x_range = range(img_shape[1])

    y_steps = y_range[0:len(y_range):output_image_height]
    x_steps = x_range[0:len(x_range):output_image_width]

    for y in y_steps:
        for x in x_steps:
            y_range = range(y, y + output_image_height)
            x_range = range(x, x + output_image_width)

            rows = np.array(y_range, dtype=np.intp)
            cols = np.array(x_range, dtype=np.intp)

            img_slc = input_image[rows[:, np.newaxis], cols]

            dst_file = output_image_path + '/neg-{0:0>4}.jpg'.format(count)

            cv2.imwrite(dst_file, img_slc)

            count += 1