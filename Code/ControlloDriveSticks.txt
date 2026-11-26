package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Drive: Stick Sinistro/Destro", group = "TeleOp")
public class ControlloDriveSticks extends LinearOpMode {

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

        // Imposta le direzioni in base al montaggio fisico dei motori
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
            // Avanti/Dietro dallo stick sinistro, Destra/Sinistra dallo stick destro
            double avantiDietro = -gamepad1.left_stick_y;
            double destraSinistra = gamepad1.right_stick_x;

            double leftPower = Range.clip(avantiDietro + destraSinistra, -1.0, 1.0);
            double rightPower = Range.clip(avantiDietro - destraSinistra, -1.0, 1.0);

            frontLeftMotor.setPower(leftPower);
            backLeftMotor.setPower(leftPower);
            frontRightMotor.setPower(rightPower);
            backRightMotor.setPower(rightPower);

            telemetry.addData("Avanti/Dietro (L-Y)", "%.2f", avantiDietro);
            telemetry.addData("Destra/Sinistra (R-X)", "%.2f", destraSinistra);
            telemetry.addData("Potenza Sinistra", "%.2f", leftPower);
            telemetry.addData("Potenza Destra", "%.2f", rightPower);
            telemetry.update();
        }

        // Ferma tutti i motori all'uscita dal ciclo
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        backRightMotor.setPower(0.0);
    }
}