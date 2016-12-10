import matplotlib.pyplot as plt
import matplotlib.image as mpimg
# import numpy as np
from skimage.feature import hog
from skimage import data, color, exposure
import argparse as ap
import cv2

parser = ap.ArgumentParser()
parser.add_argument('-i', "--inputimg", help="Path to source image", required=True)
parser.add_argument('-o', "--outputimg", help="Path to destination image", required=True)

args = vars(parser.parse_args())

inpath = args["inputimg"]
outpath = args["outputimg"]

# image = mpimg.imread(inpath, cmap=plt.cm.gray)
image = cv2.imread(inpath)
image = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)

fd, hog_image = hog(image, orientations=8, pixels_per_cell=(4, 4),
                    cells_per_block=(2, 2), visualise=True)

fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(8, 4), sharex=True, sharey=True)

ax1.axis('off')
ax1.imshow(image, cmap=plt.cm.gray)
ax1.set_title('Input image')
ax1.set_adjustable('box-forced')

# Rescale histogram for better display
hog_image_rescaled = exposure.rescale_intensity(hog_image, in_range=(0, 0.02))

mpimg.imsave(outpath, hog_image_rescaled, cmap=plt.cm.gray)

ax2.axis('off')
ax2.imshow(hog_image_rescaled, cmap=plt.cm.gray)
ax2.set_title('Histogram of Oriented Gradients')
ax1.set_adjustable('box-forced')
plt.show()