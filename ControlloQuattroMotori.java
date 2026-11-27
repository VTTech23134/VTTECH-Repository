package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Test: 4 Motori Stick e DPad", group = "Test")
public class ControlloQuattroMotori extends LinearOpMode {

    private static final double DPad_POWER = 0.7;

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    @Override
    public void runOpMode() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");

        // Imposta le direzioni in base al montaggio fisico delle ruote/motori
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Stato", "Pronto. Premi Start+A sul gamepad1.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // 1. frontLeftMotor -> Stick sinistro su/giù (in su left_stick_y è negativo)
            double flPower = -gamepad1.left_stick_y;

            // 2. frontRightMotor -> Stick destro su/giù (in su right_stick_y è negativo)
            double frPower = -gamepad1.right_stick_y;

            // 3. backLeftMotor -> D-Pad Su / Giù
            double blPower = 0.0;
            if (gamepad1.dpad_up) {
                blPower = DPad_POWER;
            } else if (gamepad1.dpad_down) {
                blPower = -DPad_POWER;
            }

            // 4. backRightMotor -> D-Pad Destra / Sinistra
            double brPower = 0.0;
            if (gamepad1.dpad_right) {
                brPower = DPad_POWER;
            } else if (gamepad1.dpad_left) {
                brPower = -DPad_POWER;
            }

            frontLeftMotor.setPower(flPower);
            frontRightMotor.setPower(frPower);
            backLeftMotor.setPower(blPower);
            backRightMotor.setPower(brPower);

            telemetry.addData("FL (Stick L)", "%.2f", flPower);
            telemetry.addData("FR (Stick R)", "%.2f", frPower);
            telemetry.addData("BL (DPad U/D)", "%.2f", blPower);
            telemetry.addData("BR (DPad R/L)", "%.2f", brPower);
            telemetry.update();
        }

        // Ferma tutti i motori all'uscita dal ciclo
        frontLeftMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
    }
}