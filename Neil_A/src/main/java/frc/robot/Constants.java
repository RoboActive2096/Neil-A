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
       public static final int Angel = 11;
       public static final int Delivery = 56;
    }
    public final class ClimbPorts{
        public static final int Elevators = 1;
        public static final int winch = 20;
    }
    public final class RuletaPorts{
        public static final int Ruleta = 3;
        public static final int wheel = 11;
    }
    public final class FourBarPorts{
        public static final int Intake = 49;
        public static final int Fourbar = 0;
    }
    public final class DriveBasePorts{
        public static final int RightOne = 12;
        public static final int RightTwo = 53;
        public static final int RightThree =14;

        public static final int LeftOne = 15;
        public static final int LeftTwo = 55;
        public static final int LeftThree = 54;
    }
}