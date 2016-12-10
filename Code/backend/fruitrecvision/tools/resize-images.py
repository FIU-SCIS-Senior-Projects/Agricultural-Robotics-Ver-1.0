import os
import cv2
import argparse as ap


parser = ap.ArgumentParser()

parser.add_argument('-i', '--input-image-path', help='path to the source image folder', required=True)
parser.add_argument('-o', '--output-image-path', help='path to the resized image folder', required=True)
parser.add_argument('-x', '--output-image-width', help='the width of the resized images', type=int, required=True)
parser.add_argument('-y', '--output-image-height', help='the height of the resized images', type=int, required=True)

args = vars(parser.parse_args())

input_image_path = args['input_image_path']
output_image_path = args['output_image_path']
output_image_width = args['output_image_width']
output_image_height = args['output_image_height']

input_image_names = [name for name in os.listdir(input_image_path) if name.endswith('.jpg') or name.endswith('.jpeg')]

for image_name in input_image_names:
    input_image = cv2.imread(input_image_path + "/" + image_name, cv2.IMREAD_GRAYSCALE)
    output_image = cv2.resize(input_image, (output_image_width, output_image_height))
    cv2.imwrite(output_image_path + "/" + image_name, output_image)

# if os.path.isdir(img_path):
# construct a dictionary of arguments
#
#
# src_paths = args['source_images'].split(',')
# dest_path = args['destination_folder']
#
# y_step_size = args['y_step_size']
# x_step_size = args['x_step_size']
#
# count = 0
#
# for src_path in src_paths:
# 	img = cv2.imread(src_path)
#
# 	img_shape = np.shape(img)
#
# 	y_range = range(img_shape[0])
# 	x_range = range(img_shape[1])
#
# 	y_steps = y_range[0:len(y_range):y_step_size]
# 	x_steps = x_range[0:len(x_range):x_step_size]
#
# 	print ("y_steps: " + str(y_steps))
# 	print ("x_steps: " + str(x_steps))
#
# 	for y in y_steps:
# 		for x in x_steps:
# 			y_range = range(y, y + y_step_size)
# 			x_range = range(x, x + x_step_size)
#
# 			print ("y_range: " + str(y_range))
# 			print ("x_range: " + str(x_range))
#
# 			rows = np.array(y_range, dtype=np.intp)
# 			cols = np.array(x_range, dtype=np.intp)
#
# 			print("(x,y): ("+str(x)+","+str(y)+")")
# 			print (img[rows[:, np.newaxis], cols])
#
# 			img_slc = img[rows[:, np.newaxis], cols]
# 			img_slc = cv2.cvtColor(img_slc,cv2.COLOR_BGR2GRAY)
#
# 			dst_file = dest_path + '/neg-' + str(count) + '.jpg'
#
# 			cv2.imwrite(dst_file, img_slc)
#
# 			count += 1
