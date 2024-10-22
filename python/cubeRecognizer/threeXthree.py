import cv2
from matplotlib import pyplot as plt


class CubeRecognizer:
    def __init__(self, file):
        self.file = file
        self.images = []
        self.cv2_images = []
        self.gray_images = []

    def get_images(self):
        with open(self.file) as f:
            lines = f.readlines()
            [self.images.append(line[:-1]) for line in lines]
        return self.images

    def set_cv2_images(self):
        if not self.images:
            raise AttributeError("Images have not been read yet.")

        [self.cv2_images.append(cv2.cvtColor(cv2.imread(img), cv2.COLOR_BGR2RGB)) for img in self.images]

    def set_gray_images(self):
        if not self.cv2_images:
            raise AttributeError("CV2Images have not been set yet.")

        [self.gray_images.append(cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)) for img in self.cv2_images]

    def recognize(self):
        if not self.gray_images:
            raise AttributeError("Some lists have not been set yet")

        edges = cv2.Canny(self.cv2_images[0], 100, 200)
        contours, _ = cv2.findContours(edges, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

        min_area = 100

        big_contours = []
        for contour in contours:
            area = cv2.contourArea(contour)
            if area > min_area:
                big_contours.append(contour)

        cv2.drawContours(self.cv2_images[0], contours, -1, (0, 255, 0), 3)

        for contour in big_contours:
            x, y, w, h = cv2.boundingRect(contour)

            if abs(w - h) < 10:
                # cut out area of square
                square = self.cv2_images[0][y:y + h, x:x + w]

                # save the color of the square
                color = cv2.mean(square)

                # draw rectangle around the found square
                cv2.rectangle(self.cv2_images[0], (x, y), (x + w, y + h), (255, 0, 0), 10)


cr = CubeRecognizer('testPicsPathFIle')

cr.get_images()
cr.set_cv2_images()
cr.set_gray_images()
cr.recognize()

plt.subplot(1, 1, 1)
plt.imshow(cr.cv2_images[0])
plt.show()
