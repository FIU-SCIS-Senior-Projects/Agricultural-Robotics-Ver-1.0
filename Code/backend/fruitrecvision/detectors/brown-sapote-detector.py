import cv2
import numpy as np

class BrownSapotiDetector():
    @staticmethod
    def count(src_img):
        dst_img = src_img.copy()

        red_less_than_green_indices = dst_img[:,:,2] <= dst_img[:,:,1]
        dst_img[red_less_than_green_indices] = 0

        green_less_than_blue_indices = dst_img[:,:,1] <= dst_img[:,:,0]
        dst_img[green_less_than_blue_indices] = 0

        dst_img = cv2.cvtColor(dst_img,cv2.COLOR_BGR2HSV)
        low_sat_indices = dst_img[:,:,1] < 30

        dst_img = cv2.cvtColor(dst_img,cv2.COLOR_HSV2BGR)
        dst_img[low_sat_indices] = 0

        kernel = np.ones((3,3),np.uint8)

        dst_img = cv2.erode(dst_img,kernel,iterations = 5)
        dst_img = cv2.dilate(dst_img,kernel,iterations = 3)

        dst_img = cv2.cvtColor(dst_img,cv2.COLOR_BGR2GRAY)

        dst_img = cv2.GaussianBlur(dst_img,(3,3),0)
        ret, dst_img = cv2.threshold(dst_img,0,255,0)

        dst_img = cv2.erode(dst_img,kernel,iterations = 5)
        dst_img = cv2.dilate(dst_img,kernel,iterations = 3)

        dst_img = cv2.GaussianBlur(dst_img,(3,3),0)
        ret, dst_img = cv2.threshold(dst_img,0,255,0)

        dst_img = cv2.GaussianBlur(dst_img,(3,3),0)
        ret, dst_img = cv2.threshold(dst_img,0,255,0)

        dst_img = cv2.erode(dst_img,kernel,iterations = 1)

        dst_img = cv2.GaussianBlur(dst_img,(3,3),0)
        ret, dst_img = cv2.threshold(dst_img,0,255,0)

        dst_img = cv2.GaussianBlur(dst_img,(3,3),0)
        ret, dst_img = cv2.threshold(dst_img,50,255,0)

        dst_img = cv2.erode(dst_img,kernel,iterations = 1)

        dst_img = cv2.GaussianBlur(dst_img,(3,3),0)
        ret, dst_img = cv2.threshold(dst_img,50,255,0)

        dst_img = cv2.GaussianBlur(dst_img,(3,3),0)
        ret, dst_img = cv2.threshold(dst_img,50,255,0)

        dst_img = cv2.erode(dst_img,kernel,iterations = 1)

        img_cntr, contours, hierarchy = cv2.findContours(img,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
        # img = cv2.drawContours(img_cntr, contours, -1, (255,255,0), 3)

        return len(contours)