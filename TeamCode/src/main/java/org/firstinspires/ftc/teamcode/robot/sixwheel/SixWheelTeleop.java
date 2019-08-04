package org.firstinspires.ftc.teamcode.robot.sixwheel;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.revextensions2.RevBulkData;

import static org.firstinspires.ftc.teamcode.common.MathUtil.clamp;
import static org.firstinspires.ftc.teamcode.common.MathUtil.deadZone;

@TeleOp
public class SixWheelTeleop extends LinearOpMode {

    SixWheelHardware robot;

    @Override
    public void runOpMode() {
        robot = new SixWheelHardware(this);
        waitForStart();

        telemetry.clearAll();
        robot.initBulkReadTelemetry();

        while (opModeIsActive()) {
            RevBulkData data = robot.performBulkRead();

            double left = deadZone(clamp(gamepad1.left_stick_y - gamepad1.right_stick_x), 0.15);
            double right = deadZone(clamp(gamepad1.left_stick_y + gamepad1.right_stick_x), 0.15);
            robot.driveLeft.setPower(left);
            robot.driveRight.setPower(right);
            robot.PTOLeft.setPower(left);
            robot.PTORight.setPower(right);
        }
    }
}