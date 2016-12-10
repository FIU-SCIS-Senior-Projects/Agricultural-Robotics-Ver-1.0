import cv2
import numpy as np
import argparse as ap

parser = ap.ArgumentParser()

parser.add_argument('-x', '--x-step-size', help='the width of each image fragment', type=int, required=True)
parser.add_argument('-y', '--y-step-size', help='the height of each image fragment', type=int, required=True)
parser.add_argument('-s', '--source-images', help='the input source image', required=True)
parser.add_argument('-d', '--destination-folder', help='the image fragment destination folder', required=True)

args = vars(parser.parse_args())

src_paths = args['source_images'].split(',')
dest_path = args['destination_folder']

y_step_size = args['y_step_size']
x_step_size = args['x_step_size']

count = 0

for src_path in src_paths:
	img = cv2.imread(src_path)

	img_shape = np.shape(img)

	y_range = range(img_shape[0])
	x_range = range(img_shape[1])

	y_steps = y_range[0:len(y_range):y_step_size]
	x_steps = x_range[0:len(x_range):x_step_size]

	for y in y_steps:
		for x in x_steps:
			y_range = range(y, y + y_step_size)
			x_range = range(x, x + x_step_size)

			rows = np.array(y_range, dtype=np.intp)
			cols = np.array(x_range, dtype=np.intp)

			img_slc = img[rows[:, np.newaxis], cols]
			img_slc = cv2.cvtColor(img_slc,cv2.COLOR_BGR2GRAY)

			dst_file = dest_path + '/neg-' + str(count) + '.jpg'

			cv2.imwrite(dst_file, img_slc)

			count += 1