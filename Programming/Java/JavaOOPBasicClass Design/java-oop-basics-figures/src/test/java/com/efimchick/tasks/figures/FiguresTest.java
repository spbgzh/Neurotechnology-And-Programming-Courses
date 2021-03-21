package com.efimchick.tasks.figures;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class FiguresTest {

    @Test
    public void testCircleConstructor() {
        new Circle(new Point(0, 0), 1);
        new Circle(new Point(1, 3), 1);
        new Circle(new Point(-23.5, 236), 56);
        new Circle(new Point(-28.5, -2), 0.001);
        new Circle(new Point(56, -87), 11.1235);
    }

    @Test(expected = RuntimeException.class)
    public void testCircleConstructorZeroRadius() {
        new Circle(new Point(0, 0), 0);
    }

    @Test(expected = RuntimeException.class)
    public void testCircleConstructorNegativeRadius() {
        new Circle(new Point(5, -6), -23);
    }

    @Test(expected = RuntimeException.class)
    public void testCircleConstructorNullCenter() {
        new Circle(null, 23);
    }

    @Test
    public void testCircleArea() {
        assertEquals(3.14159265358979323846, new Circle(new Point(0, 0), 1).area(), 0.000001);
        assertEquals(3.14159265358979323846, new Circle(new Point(1, 3), 1).area(), 0.000001);
        assertEquals(9852.03456165759, new Circle(new Point(-23.5, 236), 56).area(), 0.000001);
        assertEquals(0.00000314159265358979323846, new Circle(new Point(-28.5, -2), 0.001).area(), 0.000001);
        assertEquals(388.71633468071917, new Circle(new Point(56, -87), 11.1235).area(), 0.000001);
    }

    @Test
    public void testCircleCentroid() {
        assertEquals(0, new Circle(new Point(0, 0), 1).centroid().getX(), 0.0001);
        assertEquals(0, new Circle(new Point(0, 0), 1).centroid().getY(), 0.0001);
        assertEquals(1, new Circle(new Point(1, 3), 1).centroid().getX(), 0.0001);
        assertEquals(3, new Circle(new Point(1, 3), 1).centroid().getY(), 0.0001);
        assertEquals(-23.5, new Circle(new Point(-23.5, 236), 56).centroid().getX(), 0.0001);
        assertEquals(236, new Circle(new Point(-23.5, 236), 56).centroid().getY(), 0.0001);
        assertEquals(-28.5, new Circle(new Point(-28.5, -2), 0.001).centroid().getX(), 0.0001);
        assertEquals(-2, new Circle(new Point(-28.5, -2), 0.001).centroid().getY(), 0.0001);
        assertEquals(56, new Circle(new Point(56, -87), 11.1235).centroid().getX(), 0.0001);
        assertEquals(-87, new Circle(new Point(56, -87), 11.1235).centroid().getY(), 0.0001);
    }


    @Test
    public void testCircleToString() {
        assertEquals("Circle[(0.0,0.0)1.0]", new Circle(new Point(0, 0), 1).toString());
        assertEquals("Circle[(1.0,3.0)1.0]", new Circle(new Point(1, 3), 1).toString());
        assertEquals("Circle[(-23.5,236.0)56.0]", new Circle(new Point(-23.5, 236), 56).toString());
        assertEquals("Circle[(-28.5,-2.0)0.001]", new Circle(new Point(-28.5, -2), 0.001).toString());
        assertEquals("Circle[(56.0,-87.0)11.1235]", new Circle(new Point(56, -87), 11.1235).toString());
    }

    @Test
    public void testCircleisTheSame() {
        assertTrue(new Circle(new Point(0, 0), 1).isTheSame(new Circle(new Point(0, 0), 1)));
        assertTrue(new Circle(new Point(1 - (1d / 3 * 3), 3 - (30d / 10)), 1).isTheSame(new Circle(new Point(0, 0), 1)));
        assertTrue(new Circle(new Point(sqrt(2) * sqrt(2), 4 - sqrt(2) * sqrt(2)), sqrt(3) * sqrt(3)).isTheSame(new Circle(new Point(2, 2), 3)));

        assertFalse(new Circle(new Point(sqrt(2) * sqrt(2), 4 - sqrt(2) * sqrt(2)), sqrt(3) * sqrt(3)).isTheSame(new Circle(new Point(2.1, 2), 3)));
        assertFalse(new Circle(new Point(sqrt(2) * sqrt(2), 4 - sqrt(2) * sqrt(2)), sqrt(3) * sqrt(3)).isTheSame(new Circle(new Point(2, 2), 2.9)));
        assertFalse(new Circle(new Point(sqrt(2) * sqrt(2), 4 - sqrt(2) * sqrt(2)), sqrt(3) * sqrt(3)).isTheSame(new Circle(new Point(2, -2.0), 3)));

        assertFalse(new Circle(new Point(0, 0), 1).isTheSame(new Triangle(new Point(0, 1), new Point(1, 1), new Point(1, 0))));
        assertFalse(new Circle(new Point(0, 0), 1).isTheSame(new Quadrilateral(new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(0, 0))));
    }



    private List<Figure> figures;
    private Circle c1;
    private Circle c2;
    private Circle c3;
    private Triangle t1;
    private Triangle t2;
    private Triangle t3;
    private Quadrilateral q1;
    private Quadrilateral q2;
    private Quadrilateral q3;

    public void setup() {
        c1 = new Circle(new Point(0, 9), 7);
        c2 = new Circle(new Point(1, 9), 10);
        c3 = new Circle(new Point(-1, 9), 9.5);
        t1 = new Triangle(new Point(0, 20), new Point(20, 0), new Point(-20, 0));
        t2 = new Triangle(new Point(-15, 0), new Point(-10, 20), new Point(-5, 0));
        t3 = new Triangle(new Point(-14, 0), new Point(-10, 10), new Point(-5, 0));
        q1 = new Quadrilateral(new Point(-30, -30), new Point(-30, 30), new Point(30, 30), new Point(30, -30));
        q2 = new Quadrilateral(new Point(-20, -100), new Point(-20, 100), new Point(30, 30), new Point(30, -30));
        q3 = new Quadrilateral(new Point(-10, -10), new Point(-10, 10), new Point(10, 30), new Point(30, -10));


        figures = Arrays.asList(c1, c2, c3, t1, t2, t3, q1, q2, q3);
    }

    @Test
    public void compareByArea() {

        setup();

        final Comparator<Figure> comparator = ComparatorsCollection::compareByArea;

        testCaseMin(t3, Figure.class, comparator);
        testCaseMax(q2, Figure.class, comparator);
        testCaseMin(c1, Circle.class, comparator);
        testCaseMax(c2, Circle.class, comparator);
        testCaseMin(t3, Triangle.class, comparator);
        testCaseMax(t1, Triangle.class, comparator);
        testCaseMin(q3, Quadrilateral.class, comparator);
        testCaseMax(q2, Quadrilateral.class, comparator);
    }

    @Test
    public void compareByHorizontalStartPosition() {

        setup();

        final Comparator<Figure> comparator = ComparatorsCollection::compareByHorizontalStartPosition;

        testCaseMin(q1, Figure.class, comparator);
        testCaseMax(c1, Figure.class, comparator);
        testCaseMin(c3, Circle.class, comparator);
        testCaseMax(c1, Circle.class, comparator);
        testCaseMin(t1, Triangle.class, comparator);
        testCaseMax(t3, Triangle.class, comparator);
        testCaseMin(q1, Quadrilateral.class, comparator);
        testCaseMax(q3, Quadrilateral.class, comparator);
    }

    @Test
    public void compareByHorizontalCenterPosition() {

        setup();

        final Comparator<Figure> comparator = ComparatorsCollection::compareByHorizontalCenterPosition;

        testCaseMin(t2, Figure.class, comparator);
        testCaseMax(q3, Figure.class, comparator);
        testCaseMin(c3, Circle.class, comparator);
        testCaseMax(c2, Circle.class, comparator);
        testCaseMin(t2, Triangle.class, comparator);
        testCaseMax(t1, Triangle.class, comparator);
        testCaseMin(q1, Quadrilateral.class, comparator);
        testCaseMax(q3, Quadrilateral.class, comparator);
    }

    private <T extends Figure> void testCaseMin(final T expected, final Class<T> figureClass, final Comparator<Figure> comparator) {
        assertSame(expected, figures.stream().filter(figureClass::isInstance).min(comparator).get());
    }

    private <T extends Figure> void testCaseMax(final T expected, final Class<T> figureClass, final Comparator<Figure> comparator) {
        assertSame(expected, figures.stream().filter(figureClass::isInstance).max(comparator).get());
    }



    private static final List<Point> P1 = Arrays.asList(
            new Point(1, 1),
            new Point(2, 6),
            new Point(3, 2),
            new Point(4, 6)
    );
    private static final List<Point> P2 = Arrays.asList(
            new Point(1, -1),
            new Point(4, -6),
            new Point(5, -2),
            new Point(7, -5)
    );
    private static final List<Point> P3 = Arrays.asList(
            new Point(-1, -1),
            new Point(-8, -6),
            new Point(-4, -5),
            new Point(-9, -3)
    );
    private static final List<Point> P4 = Arrays.asList(
            new Point(-1, 1),
            new Point(-8, 8),
            new Point(-4, 4),
            new Point(-2, 6)
    );

    @Test
    public void testQuadConstructor() {
        q(0, 0, 0, 1, 1, 1, 1, 0);
        q(-2, 2, -3, 1, 0, 1, 0, 2);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNullACase() {
        q(null, new Point(-3, 1), new Point(0, 1), new Point(1, 9));
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNullBCase() {
        q(new Point(0, 1), null, new Point(-3, 1), new Point(9, 78));
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNullCCase() {
        q(new Point(-3, 1), new Point(0, 1), null, new Point(0, 0));
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNullDCase() {
        q(new Point(-3, 1), new Point(0, 1), new Point(0, 0), null);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorDegenerative1() {
        q(-1, -1, 1, 1, 2, 2, 3, -3);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorDegenerative2() {
        q(1, 3, 3, 9, 2, 6, -5, 5);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorDegenerative3() {
        q(0, 0, 0, 2, 0, 5, 1, 1);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorDegenerative4() {
        q(0, 0, 0, 0, 0, 5, 5, 0);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNotPlain1() {
        q(-1, 1, 1, -1, 1, 1, -1, -1);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNotPlain2() {
        q(-1, 1, -1, 0, 1, 0, 1, -1);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNonConvex1() {
        q(0, 0, 0, 10, 1, 1, 10, 0);
    }

    @Test(expected = RuntimeException.class)
    public void testQuadConstructorNonConvex2() {
        q(0, 0, 3, 3, 0, -3, -3, 3);
    }

    @Test
    public void testQuadArea() {

        //<editor-fold desc="expectedAreaFoldedRegion">
        final Iterator<Double> expectedArea = Arrays.asList(
                3.9999999999999982,
                17.999999999999986,
                9.999999999999998,
                9.999999999999998,
                16.00000000000002,
                72.00000000000004,
                40.00000000000003,
                42.000000000000014,
                11.000000000000004,
                49.50000000000003,
                27.499999999999993,
                26.50000000000001,
                14.000000000000021,
                63.000000000000014,
                34.99999999999999,
                41.0,
                12.000000000000007,
                25.999999999999993,
                18.000000000000007,
                18.000000000000007,
                49.000000000000014,
                105.00000000000004,
                73.00000000000003,
                75.00000000000001,
                32.5,
                71.00000000000003,
                48.999999999999986,
                48.00000000000001,
                45.00000000000004,
                94.00000000000004,
                66.00000000000001,
                72.00000000000003,
                9.000000000000002,
                15.0,
                34.50000000000003,
                58.500000000000036,
                25.5,
                41.99999999999999,
                27.00000000000003,
                48.0,
                13.999999999999996,
                27.999999999999986,
                19.999999999999996,
                55.000000000000014,
                111.00000000000003,
                79.00000000000001,
                38.999999999999986,
                77.50000000000001,
                55.49999999999998,
                46.00000000000003,
                95.00000000000003,
                67.0,
                10.0,
                44.999999999999986,
                25.000000000000014,
                21.0,
                99.00000000000004,
                55.0,
                53.000000000000014,
                16.99999999999991,
                76.49999999999997,
                42.50000000000001,
                37.5,
                89.99999999999999,
                50.0,
                52.00000000000004,
                27.99999999999999,
                62.99999999999997,
                43.0,
                38.99999999999999,
                142.0,
                97.99999999999997,
                96.0,
                48.49999999999989,
                107.99999999999996,
                73.99999999999999,
                68.99999999999997,
                130.99999999999997,
                91.0,
                93.00000000000003,
                25.500000000000004,
                60.499999999999986,
                40.500000000000014,
                36.50000000000001,
                128.00000000000006,
                84.00000000000003,
                82.00000000000003,
                41.99999999999992,
                101.49999999999999,
                67.50000000000001,
                62.50000000000001,
                113.49999999999999,
                73.5,
                75.50000000000004,
                37.0,
                71.99999999999999,
                52.000000000000014,
                48.0,
                155.00000000000006,
                111.0,
                109.00000000000003,
                61.99999999999992,
                121.49999999999999,
                87.50000000000001,
                82.5,
                139.0,
                99.00000000000001,
                101.00000000000004,
                7.0000000000000036,
                31.50000000000001,
                17.49999999999999,
                18.5,
                18.999999999999957,
                85.50000000000001,
                47.50000000000002,
                50.500000000000014,
                13.999999999999998,
                63.00000000000003,
                35.00000000000001,
                35.000000000000014,
                17.00000000000002,
                76.49999999999999,
                42.49999999999997,
                49.499999999999986,
                21.500000000000004,
                46.000000000000014,
                31.99999999999999,
                33.0,
                58.49999999999997,
                125.00000000000003,
                87.00000000000003,
                90.00000000000003,
                42.0,
                91.00000000000003,
                63.000000000000014,
                63.00000000000002,
                54.50000000000004,
                114.0,
                79.99999999999999,
                87.0,
                15.0,
                39.50000000000001,
                25.499999999999986,
                26.499999999999996,
                40.499999999999986,
                107.00000000000004,
                69.00000000000006,
                72.00000000000004,
                31.499999999999986,
                80.50000000000001,
                52.5,
                52.5,
                33.00000000000003,
                92.5,
                58.49999999999998,
                65.5,
                24.000000000000004,
                48.500000000000014,
                34.499999999999986,
                35.5,
                64.99999999999999,
                131.50000000000006,
                93.50000000000006,
                96.50000000000004,
                49.000000000000014,
                98.00000000000004,
                70.00000000000003,
                70.00000000000003,
                56.00000000000004,
                115.5,
                81.49999999999999,
                88.5,
                12.000000000000004,
                54.00000000000002,
                29.99999999999998,
                28.0,
                107.99999999999999,
                59.999999999999986,
                60.00000000000003,
                19.00000000000006,
                85.50000000000003,
                47.50000000000002,
                44.500000000000036,
                99.00000000000006,
                55.00000000000006,
                59.000000000000085,
                35.00000000000001,
                77.00000000000003,
                52.999999999999986,
                51.0,
                155.99999999999997,
                107.99999999999997,
                108.0,
                55.50000000000006,
                122.00000000000003,
                84.00000000000001,
                81.00000000000003,
                145.00000000000003,
                101.00000000000003,
                105.00000000000006,
                28.499999999999993,
                70.50000000000001,
                46.49999999999997,
                44.49999999999999,
                138.0,
                90.0,
                90.00000000000003,
                45.00000000000004,
                111.50000000000003,
                73.5,
                70.50000000000003,
                123.5,
                79.5,
                83.50000000000003,
                42.999999999999986,
                85.0,
                60.999999999999964,
                58.999999999999986,
                168.0,
                120.0,
                120.00000000000003,
                68.0,
                134.49999999999997,
                96.49999999999997,
                93.49999999999997,
                151.99999999999994,
                107.99999999999996,
                112.0
        ).iterator();
        //</editor-fold>

        cartesianProductTestCasesRun(P1, P2, P3, P4, expectedArea, this::testArea);
    }

    @Test
    public void testQuadCentroid() {
        //<editor-fold desc="expectedCentroidsFoldedRegion">
        final Iterator<Point> expectedCentroids = Arrays.asList(
                new Point(0.0, -0.0),
                new Point(-2.3333333333333335, 2.3333333333333335),
                new Point(-1.0, 1.0),
                new Point(-0.4666666666666666, 1.5333333333333332),
                new Point(-2.291666666666667, -1.7083333333333335),
                new Point(-4.625, 0.6250000000000002),
                new Point(-3.2916666666666674, -0.708333333333333),
                new Point(-2.7857142857142856, -0.16666666666666682),
                new Point(-1.0303030303030303, -1.303030303030303),
                new Point(-3.363636363636363, 1.0303030303030298),
                new Point(-2.0303030303030303, -0.30303030303030315),
                new Point(-1.4779874213836477, 0.22641509433962273),
                new Point(-2.5238095238095237, -0.8095238095238093),
                new Point(-4.857142857142857, 1.5238095238095237),
                new Point(-3.5238095238095237, 0.19047619047619044),
                new Point(-3.0894308943089435, 0.7642276422764225),
                new Point(1.0555555555555551, -1.6111111111111107),
                new Point(-1.1282051282051282, 0.8717948717948717),
                new Point(0.14814814814814814, -0.5185185185185185),
                new Point(0.4444444444444444, -0.2222222222222222),
                new Point(-1.2380952380952381, -3.333333333333333),
                new Point(-3.4000000000000004, -0.8666666666666665),
                new Point(-2.132420091324201, -2.2511415525114153),
                new Point(-1.88, -1.9066666666666665),
                new Point(0.02564102564102588, -2.902564102564103),
                new Point(-2.173708920187793, -0.408450704225352),
                new Point(-0.891156462585034, -1.8027210884353744),
                new Point(-0.5625, -1.541666666666667),
                new Point(-1.4814814814814816, -2.4592592592592593),
                new Point(-3.588652482269504, -0.035460992907801754),
                new Point(-2.343434343434343, -1.4040404040404042),
                new Point(-2.194444444444445, -0.9444444444444445),
                new Point(1.2222222222222223, -0.4444444444444445),
                new Point(0.06666666666666671, 0.4000000000000001),
                new Point(-1.0724637681159417, -2.130434782608696),
                new Point(-2.2564102564102564, -1.2735042735042734),
                new Point(0.1960784313725491, -1.7647058823529411),
                new Point(-0.9404761904761907, -0.9285714285714288),
                new Point(-1.296296296296296, -1.1851851851851851),
                new Point(-2.5625, -0.29166666666666674),
                new Point(1.952380952380952, -1.380952380952381),
                new Point(-0.523809523809524, 0.8095238095238095),
                new Point(0.8666666666666667, -0.4666666666666666),
                new Point(-0.33939393939393947, -3.078787878787879),
                new Point(-2.837837837837838, -0.8738738738738737),
                new Point(-1.4388185654008439, -2.1561181434599157),
                new Point(0.9230769230769226, -2.6923076923076916),
                new Point(-1.5376344086021505, -0.5118279569892472),
                new Point(-0.15315315315315303, -1.7837837837837838),
                new Point(-0.5652173913043479, -2.1594202898550723),
                new Point(-3.122807017543859, 0.08421052631578967),
                new Point(-1.7014925373134324, -1.2139303482587063),
                new Point(0.4666666666666666, 1.5333333333333332),
                new Point(-1.866666666666666, 3.8666666666666663),
                new Point(-0.5333333333333333, 2.5333333333333337),
                new Point(0.0, 2.8888888888888884),
                new Point(-3.787878787878787, 1.7878787878787876),
                new Point(-2.4545454545454546, 0.4545454545454546),
                new Point(-2.1194968553459117, 0.7232704402515723),
                new Point(-0.3921568627450979, 0.0588235294117646),
                new Point(-2.725490196078431, 2.392156862745098),
                new Point(-1.392156862745098, 1.0588235294117647),
                new Point(-0.9199999999999998, 1.3688888888888888),
                new Point(-3.866666666666666, 2.5333333333333328),
                new Point(-2.5333333333333337, 1.2000000000000002),
                new Point(-2.346153846153846, 1.474358974358974),
                new Point(1.488095238095238, -0.0833333333333334),
                new Point(-0.7460317460317462, 2.481481481481482),
                new Point(0.5503875968992249, 1.0620155038759689),
                new Point(0.9487179487179485, 1.1025641025641024),
                new Point(-2.63849765258216, 0.3004694835680749),
                new Point(-1.3741496598639453, -1.1156462585034015),
                new Point(-1.1666666666666665, -0.9999999999999998),
                new Point(0.6151202749140893, -1.5945017182130587),
                new Point(-1.5925925925925926, 0.9691358024691358),
                new Point(-0.3063063063063063, -0.45045045045045046),
                new Point(0.028985507246376822, -0.3913043478260871),
                new Point(-2.709923664122137, 0.9949109414758264),
                new Point(-1.4688644688644688, -0.41391941391941395),
                new Point(-1.3870967741935483, -0.2258064516129031),
                new Point(1.7647058823529411, 1.1176470588235294),
                new Point(-0.7217630853994493, 3.09366391184573),
                new Point(0.6666666666666666, 1.8888888888888888),
                new Point(1.105022831050228, 2.0228310502283104),
                new Point(-2.7031250000000004, 1.15625),
                new Point(-1.2619047619047619, -0.04761904761904764),
                new Point(-1.016260162601626, 0.11382113821138214),
                new Point(0.9285714285714285, -0.2976190476190476),
                new Point(-1.6042692939244667, 1.6699507389162562),
                new Point(-0.2, 0.4666666666666667),
                new Point(0.1786666666666667, 0.6053333333333334),
                new Point(-2.8046989720998527, 1.977973568281938),
                new Point(-1.3197278911564625, 0.7687074829931974),
                new Point(-1.2229580573951437, 0.9690949227373068),
                new Point(2.4504504504504503, 0.16216216216216225),
                new Point(0.02777777777777768, 2.2870370370370368),
                new Point(1.3974358974358976, 1.0384615384615385),
                new Point(1.7916666666666667, 1.0694444444444442),
                new Point(-1.9247311827956988, 0.2903225806451611),
                new Point(-0.5255255255255256, -0.963963963963964),
                new Point(-0.327217125382263, -0.8593272171253824),
                new Point(1.602150537634409, -1.2849462365591395),
                new Point(-0.8436213991769548, 0.8422496570644719),
                new Point(0.5352380952380952, -0.40761904761904744),
                new Point(0.8666666666666666, -0.3555555555555554),
                new Point(-2.014388489208633, 1.0791366906474817),
                new Point(-0.5925925925925926, -0.18181818181818177),
                new Point(-0.5346534653465347, -0.01320132013201322),
                new Point(0.619047619047619, 0.38095238095238093),
                new Point(-1.7142857142857142, 2.7142857142857144),
                new Point(-0.38095238095238093, 1.3809523809523812),
                new Point(0.16216216216216212, 1.954954954954955),
                new Point(-1.7017543859649122, -1.2982456140350875),
                new Point(-4.035087719298245, 1.0350877192982455),
                new Point(-2.701754385964912, -0.2982456140350879),
                new Point(-2.165016501650165, 0.27392739273927363),
                new Point(-0.49999999999999983, -0.8333333333333335),
                new Point(-2.833333333333333, 1.5),
                new Point(-1.5, 0.1666666666666666),
                new Point(-0.9000000000000001, 0.7666666666666668),
                new Point(-1.823529411764706, -0.5098039215686275),
                new Point(-4.156862745098039, 1.8235294117647056),
                new Point(-2.8235294117647065, 0.49019607843137236),
                new Point(-2.404040404040404, 1.0538720538720543),
                new Point(1.689922480620155, -1.2325581395348837),
                new Point(-0.4782608695652174, 1.2246376811594202),
                new Point(0.7916666666666667, -0.15625000000000003),
                new Point(1.0606060606060606, 0.21212121212121213),
                new Point(-0.6324786324786326, -2.914529914529914),
                new Point(-2.7973333333333334, -0.45866666666666656),
                new Point(-1.528735632183908, -1.8390804597701151),
                new Point(-1.2666666666666668, -1.4666666666666666),
                new Point(0.5833333333333333, -2.4166666666666665),
                new Point(-1.6153846153846154, 0.05128205128205151),
                new Point(-0.33333333333333315, -1.3333333333333337),
                new Point(6.344131569286608E-17, -1.0000000000000002),
                new Point(-0.7889908256880735, -2.1620795107033635),
                new Point(-2.8947368421052637, 0.26608187134502914),
                new Point(-1.65, -1.1041666666666665),
                new Point(-1.5057471264367819, -0.6551724137931034),
                new Point(1.7999999999999998, -0.06666666666666662),
                new Point(-0.7932489451476795, 2.071729957805907),
                new Point(0.6274509803921571, 0.803921568627451),
                new Point(0.9685534591194972, 1.2264150943396228),
                new Point(-0.5185185185185185, -1.740740740740741),
                new Point(-3.1183800623052957, 0.39875389408099693),
                new Point(-1.6956521739130435, -0.8695652173913042),
                new Point(-1.3611111111111114, -0.4444444444444445),
                new Point(0.6666666666666667, -1.3333333333333335),
                new Point(-1.8695652173913047, 0.7971014492753625),
                new Point(-0.46666666666666684, -0.46666666666666684),
                new Point(-0.0666666666666667, -0.0666666666666667),
                new Point(-0.5757575757575759, -0.8787878787878789),
                new Point(-3.308108108108108, 1.2882882882882882),
                new Point(-1.8461538461538463, 0.008547008547008517),
                new Point(-1.6335877862595418, 0.4860050890585242),
                new Point(2.555555555555556, -1.0),
                new Point(0.061855670103092814, 1.2130584192439864),
                new Point(1.4589371980676327, -0.072463768115942),
                new Point(1.6901408450704223, 0.2676056338028168),
                new Point(0.23589743589743595, -2.676923076923077),
                new Point(-2.261089987325729, -0.46261089987325743),
                new Point(-0.8627450980392155, -1.7486631016042784),
                new Point(-0.6390328151986182, -1.4041450777202076),
                new Point(1.4285714285714284, -2.238095238095238),
                new Point(-1.0357142857142858, -0.03571428571428559),
                new Point(0.34999999999999987, -1.3166666666666664),
                new Point(0.65, -1.0166666666666666),
                new Point(0.1428571428571429, -1.8571428571428572),
                new Point(-2.4155844155844153, 0.3823953823953825),
                new Point(-0.9938650306748467, -0.9141104294478528),
                new Point(-0.903954802259887, -0.48775894538606396),
                new Point(1.0555555555555551, 1.6111111111111107),
                new Point(-1.2777777777777775, 3.9444444444444433),
                new Point(0.05555555555555568, 2.6111111111111116),
                new Point(0.5833333333333331, 3.083333333333333),
                new Point(-3.3333333333333335, 2.0),
                new Point(-2.0, 0.6666666666666665),
                new Point(-1.5999999999999999, 1.0666666666666667),
                new Point(0.07017543859649127, 0.26315789473684204),
                new Point(-2.263157894736842, 2.596491228070175),
                new Point(-0.9298245614035087, 1.2631578947368418),
                new Point(-0.4082397003745317, 1.7303370786516852),
                new Point(-3.3636363636363638, 2.696969696969697),
                new Point(-2.0303030303030303, 1.363636363636364),
                new Point(-1.7909604519774012, 1.7344632768361579),
                new Point(2.0952380952380953, 0.0),
                new Point(-0.10822510822510832, 2.5151515151515156),
                new Point(1.1761006289308176, 1.1132075471698113),
                new Point(1.5098039215686274, 1.3137254901960784),
                new Point(-2.1538461538461537, 0.5128205128205127),
                new Point(-0.8888888888888888, -0.8888888888888888),
                new Point(-0.6666666666666666, -0.6666666666666666),
                new Point(1.1081081081081081, -1.3513513513513518),
                new Point(-1.092896174863388, 1.1639344262295082),
                new Point(0.19047619047619047, -0.23809523809523814),
                new Point(0.5185185185185185, -0.03703703703703701),
                new Point(-2.1816091954022987, 1.1563218390804597),
                new Point(-0.9405940594059407, -0.2409240924092409),
                new Point(-0.8476190476190476, 0.02857142857142862),
                new Point(2.3157894736842097, 1.1754385964912277),
                new Point(-0.22222222222222246, 3.222222222222222),
                new Point(1.1827956989247317, 1.989247311827957),
                new Point(1.5655430711610485, 2.258426966292135),
                new Point(-2.3043478260869565, 1.36231884057971),
                new Point(-0.8666666666666666, 0.13333333333333336),
                new Point(-0.6000000000000001, 0.4000000000000001),
                new Point(1.3333333333333333, -0.1666666666666666),
                new Point(-1.209267563527653, 1.8789237668161436),
                new Point(0.19727891156462599, 0.6462585034013606),
                new Point(0.574468085106383, 0.9148936170212766),
                new Point(-2.3508771929824563, 2.1417004048582995),
                new Point(-0.8679245283018868, 0.9119496855345913),
                new Point(-0.7544910179640718, 1.1956087824351296),
                new Point(3.0232558139534884, 0.2325581395348836),
                new Point(0.5686274509803921, 2.3960784313725494),
                new Point(1.9508196721311477, 1.1311475409836067),
                new Point(2.2655367231638417, 1.3050847457627122),
                new Point(-1.5000000000000002, 0.5),
                new Point(-0.09999999999999999, -0.7666666666666669),
                new Point(0.09999999999999998, -0.5666666666666667),
                new Point(2.0392156862745097, -1.1127450980392157),
                new Point(-0.4175960346964063, 1.0508054522924413),
                new Point(0.9654576856649397, -0.21416234887737476),
                new Point(1.2745098039215688, -0.03921568627450955),
                new Point(-1.537280701754386, 1.2434210526315788),
                new Point(-0.11419753086419755, -0.027777777777777762),
                new Point(-0.056547619047619076, 0.2172619047619048)
        ).iterator();
        //</editor-fold>

        cartesianProductTestCasesRun(P1, P2, P3, P4, expectedCentroids, this::testCentroid);
    }

    private <T_T> void cartesianProductTestCasesRun(final List<Point> p1,
                                                    final List<Point> p2,
                                                    final List<Point> p3,
                                                    final List<Point> p4,
                                                    final Iterator<T_T> expected,
                                                    final BiConsumer<Quadrilateral, T_T> assertionRoutine) {
        for (Point a : p1) {
            for (Point b : p2) {
                for (Point c : p3) {
                    for (Point d : p4) {
                        try {
                            final Quadrilateral q = q(a, b, c, d);
                            assertionRoutine.accept(q, expected.next());
                        } catch (RuntimeException re) {
                            //non-convex case, no matter
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testQuadTheSame() {

        Point a = new Point(0, 0);
        Point b = new Point(1, 10);
        Point c = new Point(11, 11);
        Point d = new Point(19, 2);

        final Quadrilateral abcd = q(a, b, c, d);
        final Quadrilateral abcd_clone = q(new Point(0, 0), new Point(1, 10), new Point(11, 11), new Point(19, 2));
        final Quadrilateral bcda = q(b, c, d, a);
        final Quadrilateral badc = q(b, a, d, c);
        final Quadrilateral cbad = q(c, b, a, d);

        assertTrue(abcd.isTheSame(abcd_clone));
        assertTrue(abcd.isTheSame(bcda));
        assertTrue(abcd.isTheSame(badc));
        assertTrue(abcd.isTheSame(cbad));

        assertTrue(abcd_clone.isTheSame(abcd));
        assertTrue(bcda.isTheSame(abcd));
        assertTrue(badc.isTheSame(abcd));
        assertTrue(cbad.isTheSame(abcd));

        assertFalse(abcd.isTheSame(q(a, b, c, new Point(d.getX() + 1, d.getY()))));
        assertFalse(abcd.isTheSame(q(a, new Point(-b.getX(), b.getY()), c, d)));

        assertTrue(abcd.isTheSame(q(a, b, c, new Point(d.getX(), sqrt(2) * sqrt(2)))));
        assertTrue(abcd.isTheSame(q(c, new Point(d.getX(), sqrt(2) * sqrt(2)), a, b)));
    }

    private Quadrilateral q(final Point a, final Point b, final Point c, final Point d) {
        return new Quadrilateral(a, b, c, d);
    }

    private void testArea(final Quadrilateral t, final double expected) {
        assertEquals("Error in area() on case " + t, expected, t.area(), 0.0001);
    }

    private void testCentroid(final Quadrilateral t, final Point expected) {
        final Point centroid = t.centroid();

        assertEquals("Error in centroid() on case (X) " + t, expected.getX(), centroid.getX(), 0.0001);
        assertEquals("Error in centroid() on case (Y) " + t, expected.getY(), centroid.getY(), 0.0001);
    }

    private Quadrilateral q(final double ax, final double ay, final double bx, final double by, final double cx, final double cy, final double dx, final double dy) {
        return q(new Point(ax, ay), new Point(bx, by), new Point(cx, cy), new Point(dx, dy));
    }



    @Test
    public void testTriConstructor() {
        t(0, 0, 1, 1, 0, 1);
        t(-2, 2, -3, 1, 0, 1);
    }

    @Test(expected = RuntimeException.class)
    public void testTriConstructorNullACase() {
        new Triangle(null, new Point(-3, 1), new Point(0, 1));
    }

    @Test(expected = RuntimeException.class)
    public void testTriConstructorNullBCase() {
        new Triangle(new Point(0, 1), null, new Point(-3, 1));
    }

    @Test(expected = RuntimeException.class)
    public void testTriConstructorNullCCase() {
        new Triangle(new Point(-3, 1), new Point(0, 1), null);
    }

    @Test(expected = RuntimeException.class)
    public void testTriConstructorDegenerative1() {
        t(-1, -1, 1, 1, 2, 2);
    }

    @Test(expected = RuntimeException.class)
    public void testTriConstructorDegenerative2() {
        t(1, 3, 3, 9, 2, 6);
    }

    @Test(expected = RuntimeException.class)
    public void testTriConstructorDegenerative3() {
        t(0, 0, 0, 2, 0, 5);
    }

    @Test(expected = RuntimeException.class)
    public void testTriConstructorDegenerative4() {
        t(0, 0, 0, 0, 0, 5);
    }

    @Test
    public void testTriArea() {
        testArea(6.00, 0, 0, 4, 0, 0, 3);
        testArea(4.50, 0, 1, 0, 4, 3, 0);
        testArea(18.0, 2, 5, -5, 4, 3, 0);
        testArea(35.0, 8, 2, 1, 2, -2, -8);
        testArea(13.0, 4, 5, 2, 5, 3, -8);
        testArea(24.5, 9, 7, 6, 9, 7, -8);
        testArea(7.50, 4, 9, 4, 6, 9, -8);
        testArea(5.00, 6, 3, 7, 3, -3, -7);
        testArea(3.00, 3, 5, 9, 3, 6, 5);
        testArea(10.0, 8, 2, 3, 7, 3, 3);
        testArea(4.00, 7, 7, 4, 0, 5, 5);
        testArea(15.5, 3, 4, 8, 2, 6, 9);
    }

    @Test
    public void testTriCentroid() {
        testCentroid(0, 0, 4, 0, 0, 3, new Point(1.3333333333333333, 1.0));
        testCentroid(0, 1, 0, 4, 3, 0, new Point(1.0, 1.6666666666666667));
        testCentroid(2, 5, -5, 4, 3, 0, new Point(0.0, 3.0));
        testCentroid(8, 2, 1, 2, -2, -8, new Point(2.3333333333333335, -1.3333333333333333));
        testCentroid(4, 5, 2, 5, 3, -8, new Point(3.0, 0.6666666666666666));
        testCentroid(9, 7, 6, 9, 7, -8, new Point(7.333333333333333, 2.6666666666666665));
        testCentroid(4, 9, 4, 6, 9, -8, new Point(5.666666666666667, 2.3333333333333335));
        testCentroid(6, 3, 7, 3, -3, -7, new Point(3.3333333333333335, -0.3333333333333333));
        testCentroid(3, 5, 9, 3, 6, 5, new Point(6.0, 4.333333333333333));
        testCentroid(8, 2, 3, 7, 3, 3, new Point(4.666666666666667, 4.0));
        testCentroid(7, 7, 4, 0, 5, 5, new Point(5.333333333333333, 4.0));
        testCentroid(3, 4, 8, 2, 6, 9, new Point(5.666666666666667, 5.0));
    }

    @Test
    public void testTriTheSame() {
        final Triangle abc = t(0, 0, 1, 10, 19, 2);
        final Triangle abc_clone = t(0, 0, 1, 10, 19, 2);
        final Triangle bca = t(1, 10, 19, 2, 0, 0);
        final Triangle bac = t(1, 10, 0, 0, 19, 2);
        final Triangle cba = t(19, 2, 1, 10, 0, 0);

        assertTrue(abc.isTheSame(abc_clone));
        assertTrue(abc.isTheSame(bca));
        assertTrue(abc.isTheSame(bac));
        assertTrue(abc.isTheSame(cba));

        assertTrue(abc_clone.isTheSame(abc));
        assertTrue(bca.isTheSame(abc));
        assertTrue(bac.isTheSame(abc));
        assertTrue(cba.isTheSame(abc));

        assertFalse(abc.isTheSame(t(0, 0, 1, 10, 19, 3)));
        assertFalse(abc.isTheSame(t(0, 0.1, 1, 10, 19, 2)));
        assertFalse(abc.isTheSame(t(0, 0, 1, -10, 19, 2)));

        assertTrue(abc.isTheSame(t(0, 0, 1, 10, 19, sqrt(2) * sqrt(2))));
        assertTrue(abc.isTheSame(t(0, 0, 19, sqrt(2) * sqrt(2), 1, 10)));

        assertFalse(abc.isTheSame(new Circle(new Point(0,0), 4)));
        assertFalse(abc.isTheSame(new Quadrilateral(new Point(0,0), new Point(1, 0), new Point(1,1), new Point(0, 1))));
    }

    private void testArea(final double expected, final double ax, final double ay, final double bx, final double by, final double cx, final double cy) {
        final Triangle t = t(ax, ay, bx, by, cx, cy);
        assertEquals("Error in area() on case " + t, expected, t.area(), 0.0001);
    }

    private void testCentroid(final double ax, final double ay, final double bx, final double by, final double cx, final double cy, final Point expected) {
        final Triangle t = t(ax, ay, bx, by, cx, cy);
        final Point centroid = t.centroid();
        assertEquals("Error in centroid() on case (X) " + t, expected.getX(), centroid.getX(), 0.0001);
        assertEquals("Error in centroid() on case (Y) " + t, expected.getY(), centroid.getY(), 0.0001);
    }

    private Triangle t(final double ax, final double ay, final double bx, final double by, final double cx, final double cy) {
        return new Triangle(new Point(ax, ay), new Point(bx, by), new Point(cx, cy));
    }


}
