package com.example.opencvlearning.demo.image_processing

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.opencvlearning.R
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

class BasicDrawingActivity : AppCompatActivity() {
    private lateinit var iv1: AppCompatImageView;
    private lateinit var iv2: AppCompatImageView;

    companion object {
        private const val intW: Int = 400
        private const val doubleW: Double = 400.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_drawing)
        iv1 = findViewById(R.id.iv_basic_drawing_1)
        iv2 = findViewById(R.id.iv_basic_drawing_2)

        val atom_image = Mat.zeros(intW, intW, CvType.CV_8UC3);
        val rook_image = Mat.zeros(intW, intW, CvType.CV_8UC3);

        myEllipse(atom_image, 90.0);
        myEllipse(atom_image, 0.0)
        myEllipse(atom_image, 45.0)
        myEllipse(atom_image, -45.0)

        myFilledCircle(atom_image, Point(intW.toDouble() / 2, intW.toDouble() / 2))
        myPolygon(rook_image)

        Imgproc.rectangle(rook_image, Point(0.0, 7 * doubleW / 8), Point(doubleW, doubleW), Scalar(0.0, 255.0, 255.0), -1, 8, 0)

        myLine(rook_image, Point(0.0, 15 * doubleW / 16), Point(doubleW, 15 * doubleW / 16))
        myLine(rook_image, Point(doubleW / 4, 7 * doubleW / 8), Point(doubleW / 4, doubleW))
        myLine(rook_image, Point(doubleW / 2, 7 * doubleW / 8), Point(doubleW / 2, doubleW))
        myLine(rook_image, Point(3 * doubleW / 4, 7 * doubleW / 8), Point(3 * doubleW / 4, doubleW))

        val bitmap1 = Bitmap.createBitmap(atom_image.width(), atom_image.height(), Bitmap.Config.ARGB_8888)
        val bitmap2 = Bitmap.createBitmap(rook_image.width(), rook_image.height(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(atom_image, bitmap1)
        Utils.matToBitmap(rook_image, bitmap2)
        iv1.setImageBitmap(bitmap1)
        iv2.setImageBitmap(bitmap2)


    }

    private fun myLine(img: Mat?, start: Point, end: Point) {
        val thickness = 2
        val lineType = 8
        val shift = 0

        Imgproc.line(img, start, end, Scalar(0.0, 0.0, 0.0), thickness, lineType, shift)

    }

    private fun myPolygon(img: Mat?) {
        val lineType = 8
        val shift = 0
        val rook_points = arrayOf(
                Point(doubleW / 4, 7 * doubleW / 8),
                Point(3 * doubleW / 4, 7 * doubleW / 8),
                Point(3 * doubleW / 4, 13 * doubleW / 16),
                Point(11 * doubleW / 16, 13 * doubleW / 16),
                Point(19 * doubleW / 32, 3 * doubleW / 8),
                Point(3 * doubleW / 4, 3 * doubleW / 8),
                Point(3 * doubleW / 4, doubleW / 8),
                Point(26 * doubleW / 40, doubleW / 8),
                Point(26 * doubleW / 40, doubleW / 4),
                Point(22 * doubleW / 40, doubleW / 4),
                Point(22 * doubleW / 40, doubleW / 8),
                Point(18 * doubleW / 40, doubleW / 8),
                Point(18 * doubleW / 40, doubleW / 4),
                Point(14 * doubleW / 40, doubleW / 4),
                Point(14 * doubleW / 40, doubleW / 8),
                Point(doubleW / 4, doubleW / 8),
                Point(doubleW / 4, 3 * doubleW / 8),
                Point(13 * doubleW / 32, 3 * doubleW / 8),
                Point(5 * doubleW / 16, 13 * doubleW / 16),
                Point(doubleW / 4, 13 * doubleW / 16),
        )

        val matPt = MatOfPoint()
        matPt.fromArray(*rook_points)

        val ppt = listOf(matPt)
        Imgproc.fillPoly(img, ppt,
                Scalar(255.0, 255.0, 255.0),
                lineType,
                shift,
                Point(0.0, 0.0))
    }

    private fun myFilledCircle(img: Mat?, center: Point) {
        val thickness = -1
        val lineType = 8
        val shift = 0

        Imgproc.circle(img, center, intW / 32,
                Scalar(0.0, 0.0, 255.0),
                thickness,
                lineType,
                shift)
    }

    private fun myEllipse(img: Mat?, angle: Double) {
        val thickness = 2
        val lineType = 8
        val shift = 0

        Imgproc.ellipse(img,
                Point(intW.toDouble() / 2, intW.toDouble() / 2),
                Size(intW.toDouble() / 4, intW.toDouble() / 16),
                angle,
                0.0,
                360.0,
                Scalar(255.0, 0.0, 0.0),
                thickness,
                lineType,
                shift)
    }
}