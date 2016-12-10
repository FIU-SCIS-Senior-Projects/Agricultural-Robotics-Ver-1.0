The code is divided into five subdirectories of the Code directory:

1. backend, which contains:
	1.1 the fruitrecsite Django/Graphine web application, and 
	1.2 the fruitrecvision computer vision module
2. mobile, which contains the Android client app
3. tools, which contains software used to experiment with the drone's capabilities outside the scope of the FruiTREC Android app, and
4. web-dev, which contains the website

fruitrecsite is further subdivided into:

	1. the django application files,and
	2. tutorials for graphene-django development

fruitrecvision is further divided into:

	1. detectors, including:
		1.1 the HOG-based Longan detector, and
		1.2 the segmentation-based Sapote detector, and
	2. tools, for automating the construction of datasets for the SVM

tools contains two ARDrone applications:
	1. one for piloted flight
	2. and one pre-programmed flight