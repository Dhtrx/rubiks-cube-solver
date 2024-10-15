import cv2


def get_images(file):
    global images
    with open(file) as f:
        lines = f.readlines()
        [images.append(line) for line in lines]


images = []


