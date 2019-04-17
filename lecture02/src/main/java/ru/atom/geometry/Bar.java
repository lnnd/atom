package ru.atom.geometry;

import java.util.Objects;

public class Bar implements Collider {

    private Point firstCorner;
    private Point secondCorner;

    private int leftX;
    private int rightX;
    private int upY;
    private int downY;

    public Bar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {

        this.firstCorner = new Point(firstCornerX, firstCornerY);
        this.secondCorner = new Point(secondCornerX, secondCornerY);

        leftX   = Math.min(firstCorner.getX(), secondCorner.getX());
        rightX  = Math.max(firstCorner.getX(), secondCorner.getX());
        upY     = Math.max(firstCorner.getY(), secondCorner.getY());
        downY   = Math.min(firstCorner.getY(), secondCorner.getY());

    }

    @Override
    public boolean isColliding(Collider other) {

        boolean result;

        if (other instanceof Point) {
            result = checkPoint(((Point) other).getX(), ((Point) other).getY());
        }
        else {
            result = checkRect((Bar) other);
        }

        return result;
    }

    public boolean checkRect(Bar other) {

        int o_leftX = other.getLeftX();
        int o_rightX = other.getRightX();
        int o_upY = other.getUpY();
        int o_downY = other.getDownY();

        if (leftX > o_rightX || rightX < o_leftX)
            return false;
        if (upY < o_downY || downY > o_upY)
            return false;

        return true;
    }

    public boolean checkPoint(int x, int y){
        return leftX <= x & x <= rightX & upY >= y & y >= downY;
    }

    public Point getFirstCorner() {
        return firstCorner;
    }

    public Point getSecondCorner() {
        return secondCorner;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getRightX() {
        return rightX;
    }

    public int getUpY() {
        return upY;
    }

    public int getDownY() {
        return downY;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bar bar = (Bar) o;
        return leftX == bar.leftX &&
                rightX == bar.rightX &&
                upY == bar.upY &&
                downY == bar.downY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftX, rightX, upY, downY);
    }
}
