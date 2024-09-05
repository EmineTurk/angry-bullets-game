
import java.awt.event.KeyEvent;

/**
 * @author EmineTurk student ıd:2022400228
 * @version 1.0
 * Angry bullets game:Visualize a game that has targets and obstacles which should be shooted by bullets.Space key is started game and r provides game to replay.
 */

public class EmineTurk {
    public static void main(String[] args) {
        int width = 1600; //screen width
        int height = 800; // screen height
        double gravity =  9.80665; // gravity
        double x0 = 120; // x and y coordinates of the bullet’s starting position on the platform
        double y0 = 120;
        double bulletVelocity = 180; // initial velocity
        double bulletAngle = 45.0; // initial angle
        double[][] obstacleArray = {
                {1200, 0, 60, 220},
                {1000, 0, 60, 160},
                {600, 0, 60, 80},
                {600, 180, 60, 160},
                {220, 0, 120, 180}
        };
        double[][] targetArray = {
                {1160, 0, 30, 30},
                {730, 0, 30, 30},
                {150, 0, 20, 20},
                {1480, 0, 60, 60},
                {340, 80, 60, 30},
                {1500, 600, 60, 60}
        };
        //double[][] obstacleArray = {
        //               {1100, 0, 60, 220},
        //                {800, 0, 60, 120},
        //                {600, 0, 60, 80},
        //                {500, 180, 60, 130},
        //               {180, 0, 120, 180}
        //};
        //        double[][] targetArray = {
        //                {1160, 0, 30, 20},
        //                {730, 0, 30, 30},
        //                {150, 0, 20, 30},
        //               {1300, 0, 60, 60},
        //                {340, 80, 60, 30},
        //               {1200, 600, 60, 50}
        //        };

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, 1600);
        StdDraw.setYscale(0, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.DARK_GRAY);

        double t = 0.096;
        StdDraw.point(x0, y0);
        boolean writecontroller=false;
        boolean hitthetarget;


        while (true) {
            StdDraw.pause(50);
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            hitthetarget = false;
            boolean spaceController = false;

           if(!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)&& (writecontroller==false)){//if user did not press to space and shooting sımulation finished clear.

                StdDraw.clear();
            }


            t = 0.0099999999999;
            x0 = 120;
            y0 = 120;
            for (int i = 0; i < obstacleArray.length; i++) {//draws obstacles
                double x = obstacleArray[i][0];
                double y = obstacleArray[i][1];
                double widthh = obstacleArray[i][2];
                double heighth = obstacleArray[i][3];
                StdDraw.filledRectangle(x + widthh / 2, y + heighth / 2, widthh / 2, heighth / 2);
            }
            StdDraw.setPenColor(StdDraw.ORANGE);
            for (int b = 0; b < targetArray.length; b++) {//draws targets
                double x = targetArray[b][0];
                double y = targetArray[b][1];
                double width1 = targetArray[b][2];
                double height1 = targetArray[b][3];
                StdDraw.filledRectangle(x + width1 / 2, y + height1 / 2, width1 / 2, height1 / 2);
            }


            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(60, 60, 60, 60);//draws shooting platform
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {//changes velocity and angle according to pressed keys.
                bulletAngle = bulletAngle + 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                bulletAngle -= 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                bulletVelocity -= 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                bulletVelocity += 1;
            }
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(55.0, 60.0, "a: " + bulletAngle);//writes angle and velocity on shooting platform
            StdDraw.text(58.5, 40.0, "v: " + bulletVelocity);

            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                spaceController = true;
            }
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            double shootlinelength = bulletVelocity/4;
            double shootline_x=x0+Math.cos(Math.toRadians(bulletAngle))*shootlinelength;
            double shootline_y=y0+Math.sin(Math.toRadians(bulletAngle))*shootlinelength;
            StdDraw.line(x0,y0,shootline_x,shootline_y);//draws shooting line


            StdDraw.show();




            if (spaceController) {
                while (true) {

                    StdDraw.setPenRadius(0.01);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    boolean obstacleController = false;
                    double x1 = x0 + bulletVelocity/1.75 * Math.cos(Math.toRadians(bulletAngle)) * t;//determines current x position
                    double y1 = y0 + bulletVelocity/1.75* (Math.sin(Math.toRadians(bulletAngle))) * t - ((0.5) * (gravity*50) * Math.pow(t, 2));//determines current y position
                    StdDraw.setPenRadius(0.02);
                    StdDraw.point(x1, y1);//draws point at current position
                    StdDraw.setPenRadius(0.001);
                    StdDraw.line(x0, y0, x1, y1);//draws lines between points
                    StdDraw.show();
                    t += 0.0099999999999;
                    x0 = x1;//sets x position to final position
                    y0 = y1;//sets y position to final position


                    for (int i = 0; i < targetArray.length; i++) {
                        double x2 = targetArray[i][0];
                        double y2 = targetArray[i][1];
                        double width2 = targetArray[i][2];
                        double height2 = targetArray[i][3];
                        double xend = x2 + width2;
                        double yend = y2 + height2;
                        if ((x1 <= xend) && (x1 >= x2) && (y1 <= yend) && (y1 >= y2)) {//if x and y position overlaps target's position writes hit the target
                            StdDraw.text(130, 780, "Congratulations: You hit the target!");
                            spaceController = false;
                            writecontroller = true;
                            hitthetarget=true;
                            break;

                        }
                    }
                    for (int c = 0; c < obstacleArray.length; c++) {//if x and y position overlaps obstacle's position writes hit the obstacle
                        double x3 = obstacleArray[c][0];
                        double y3 = obstacleArray[c][1];
                        double width3 = obstacleArray[c][2];
                        double height3 = obstacleArray[c][3];
                        double xend3 = x3 + width3;
                        double yend3 = y3 + height3;
                        if ((x1 <= xend3) && (x1 >= x3) && (y1 <= yend3) && (y1 >= y3)) {
                            StdDraw.text(132, 780, "Hit an obstacle press r to shoot again");
                            StdDraw.show();


                            spaceController = false;//it turns true when space is pressed
                            writecontroller = true;//it turns true to prevent clearing whole screen before shooting finish
                            hitthetarget=true;//it turns true when hit a target to prevent writing hit the ground on screen at the same time.
                            break;


                        }


                    }

                    if (hitthetarget)//finishes simulation if hit an obstacle or target
                        break;
                    if (width>1600){
                        break;
                    }

                    if (y0 <= 0 || y1 <= 0 || (y0 > 0 && y1 < 0)) {
                        if (!hitthetarget) {
                            StdDraw.text(132, 780, "Hit the ground press 'r' to shoot again.");
                            StdDraw.show();
                            spaceController = false;
                            writecontroller = true;
                            break;
                        }

                    } else if (x0 > 1600 || x1 > 1600) {
                        if(!hitthetarget) {
                            StdDraw.text(150, 780, "Max X reached. press 'r' to shoot again.");
                            spaceController = false;
                            writecontroller = true;
                            break;
                        }
                    }




                }
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_R)) {//if r key is pressed clears screen and initializes velocity and angle
                writecontroller = false;
                StdDraw.clear();
                bulletAngle = 45;
                bulletVelocity = 180;
            }

        }

    }




}


