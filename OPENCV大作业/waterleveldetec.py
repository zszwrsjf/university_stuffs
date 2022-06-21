
import numpy as np
import cv2
import matplotlib.pyplot as plt

pic = 0
while(1):
    pic += 1
    filename = "bottlepic/bottleg" + str(pic) + ".jpg"

    image = cv2.imread(filename)
    imgHSV = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

    image = image[: int(image.shape[0] / 2), ]

    mask = np.zeros((image.shape[0], image.shape[1], image.shape[2]), dtype=np.uint8)
    mask = mask + 200
    for i in range(mask.shape[0]):
        for j in range(mask.shape[1]):
            if(i<mask.shape[0]/5):
                mask[i][j] = 0
            else:
                if((mask[i][j] < image[i][j]).all()):
                    mask[i][j] = 255
                else:
                    mask[i][j] = 0

    kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (30, 5))
    mask = cv2.dilate(mask, kernel)

    cv2.illuminationChange(image, mask, image)
    cv2.illuminationChange(image, mask, image)
    cv2.illuminationChange(image, mask, image)

    img = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    img = cv2.bilateralFilter(img,3,10,3)

    canny = cv2.Canny(img, 55, 110, apertureSize=3)
    cv2.imshow("",canny)
    cv2.waitKey(0)
    kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (3, 3))
    canny = cv2.dilate(canny, kernel)
    cv2.imshow("",canny)
    cv2.waitKey(0)
    lines = cv2.HoughLinesP(canny, rho=1, theta=np.pi / 180, threshold=110, minLineLength=130, maxLineGap=3)

    t = 0
    T = len(lines)
    while(t<T):
        for x1, y1, x2, y2 in lines[t]:
            if abs(x2-x1) <= abs(y2-y1):
                lines = np.delete(lines, t, axis=0)
                t -= 1
                T -= 1
                continue
            cv2.line(image, (x1, y1), (x2, y2), (155, 0, 0), 1)
        t += 1

    line = np.sum(lines, axis=0)
    line = (line / len(lines)).astype(int)

    levely = 0
    for x1, y1, x2, y2 in line:
        ny1 = int((y1*x2-y2*x1)/(x2-x1))
        ny2 = int((y2-y1)*(image.shape[1]-x1)/(x2-x1)+y1)
        levely = int((ny1 + ny2)/2)
        cv2.line(image, (0, ny1), (image.shape[1], ny2), (255, 0, 0), 1)

    iLowH = 50
    iHighH = 80
    iLowS = 120
    iHighS = 255
    iLowV = 40
    iHighV = 255

    thresholded = cv2.inRange(imgHSV,(iLowH,iLowS,iLowV),(iHighH,iHighS,iHighV))

    kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (5, 5))
    thresholded = cv2.erode(thresholded, kernel,thresholded)
    thresholded = cv2.dilate(thresholded, kernel,thresholded)

    h1, w1 = thresholded.shape
    contours, cnt = cv2.findContours(thresholded.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    t = 0
    Area = 0
    Cnt = contours[0]
    conlen = len(contours)
    while(t < conlen):
        cnt = contours[t]
        area = cv2.contourArea(cnt)
        if area > Area :
            Area = area
            Cnt = cnt
        t += 1


    M = cv2.moments(Cnt)
    center_x = int(M["m10"] / M["m00"])
    center_y = int(M["m01"] / M["m00"])
    cv2.line(image,(0,center_y),(image.shape[1],center_y),(0,0,255),1)
    cv2.imshow("",image)

    cv2.line(image,(int(image.shape[1]/2),center_y),(int(image.shape[1]/2),levely),(128,0,128),3)
    high = levely - center_y
    print(high)
    cv2.imshow("",image)
    cv2.waitKey(0)