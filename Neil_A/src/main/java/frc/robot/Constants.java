/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class ShootersPorts{
       public static final int Shooter = 52;
       public static final int Loading = 50; 
       public static final int Angle = 56;
       public static final int Delivery = 49;
    }
    public final class ClimbPorts{
        public static final int Elevators = 47;
        public static final int winch = 0;
    }
    public final class RuletaPorts{
        public static final int Ruleta = 46;
        public static final int wheel = 21;
    }
    public final class FourBarPorts{
        public static final int Intake = 48;
        public static final int Fourbar = 11;
    }
    public final class DriveBasePorts{
        public static final int RightOne = 12;
        public static final int RightTwo = 53;
        public static final int RightThree =14;

        public static final int LeftOne = 15;
        public static final int LeftTwo = 55;
        public static final int LeftThree = 54;
    }

    public final class XboxButtons{
        public static final int ButtonA = 1;
        public static final int ButtonB = 2;
        public static final int ButtonX = 3;
        public static final int ButtonY = 4;
        public static final int ButtonLB = 5;
        public static final int ButtonRB = 6;
        public static final int ButtonLeftSmall = 7;
        public static final int ButtonRightSmall = 8;
        
        public static final int ButtonLeftAxisButton = 9;
        public static final int ButtonRightAxisButton = 10;
        
        public static final int PovButtonUp = 0;
        public static final int PovButtonDown = 180;
        public static final int RawAxisYLeft = 1;
        public static final int RawAxisXLeft = 0;
        public static final int RawAxisYRight = 5;
        public static final int RawAxisXRight = 4;
        public static final int RawAxisRT =2;
        public static final int RawAxisLT =3;
    }
}