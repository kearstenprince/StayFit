package com.example.stayfit;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class BMICalcUtils   {

    @PrimaryKey(autoGenerate = true)
    int bmiID;

    public int getBmiID() {
        return bmiID;
    }

    public void setBmiID(int bmiID) {
        this.bmiID = bmiID;
    }

    public static final BMICalcUtils instance = new BMICalcUtils();
    private static final int CENTIMETERS_IN_METER = 100;
    private static final int INCHES_IN_FOOT = 12;
    private static final int BMI_IMPERIAL_WEIGHT_SCALAR = 703;

    public static final String BMI_CATEGORY_UNDERWEIGHT = "Underweight";
    public static final String BMI_CATEGORY_HEALTHY = "Healthy Weight";
    public static final String BMI_CATEGORY_OVERWEIGHT = "Overweight";
    public static final String BMI_CATEGORY_OBESE = "Obese";

    public static BMICalcUtils getInstance() {
        return instance;
    }
    public double calculateBMIMetric(double heightCm, double weightKg) {
        return (weightKg / ((heightCm / CENTIMETERS_IN_METER) * (heightCm / CENTIMETERS_IN_METER)));
    }

    public double calculateBMIImperial(double heightFeet, double heightInches, double weightLbs) {
        double totalHeightInInches = (heightFeet * INCHES_IN_FOOT) + heightInches;
        return (BMI_IMPERIAL_WEIGHT_SCALAR * weightLbs) / (totalHeightInInches * totalHeightInInches);
    }

    public String classifyBMI(double bmi) {
        if (bmi < 18.5) {
            return BMI_CATEGORY_UNDERWEIGHT;
        } else if (bmi >= 18.5 && bmi < 25) {
            return BMI_CATEGORY_HEALTHY;
        } else if (bmi >= 25 && bmi < 30){
            return BMI_CATEGORY_OVERWEIGHT;
        } else {
            return BMI_CATEGORY_OBESE;
        }
    }
}