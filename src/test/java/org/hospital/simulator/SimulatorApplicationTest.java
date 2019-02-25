package org.hospital.simulator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyChar;

public class SimulatorApplicationTest {

    @Test
    public void main() {


    }

    @Test
    public void stateAfterTreatmentIfAnyPatientGetsAsAndP() {
        String[] drugs = {"As", "P"};
        Assert.assertEquals('X', SimulatorApplication.stateAfterTreatment('F', drugs));
    }

    @Test
    public void stateAfterTreatmentIfhealthyPatientGetsIAndAn() {
        String[] drugs = {"I", "An"};
        Assert.assertEquals('F', SimulatorApplication.stateAfterTreatment('H', drugs));
    }

    @Test
    public void stateAfterTreatmentIfDiabeticPatientGetsIAndAn() {
        String[] drugs = {"I", "An"};
        Assert.assertEquals('D', SimulatorApplication.stateAfterTreatment('D', drugs));
    }

    @Test
    public void stateAfterTreatmentIfPatientWithFieverGetsPAndIAndAn() {
        String[] drugs = {"I", "An", "P"};
        Assert.assertEquals('F', SimulatorApplication.stateAfterTreatment('H', drugs));
    }

    @Test
    public void stateAfterTreatmentIfPatientWithTuberculosisGetsAn() {
        String[] drugs = {"An", "P"};
        Assert.assertEquals('H', SimulatorApplication.stateAfterTreatment('T', drugs));
    }

    @Test
    public void stateAfterTreatmentIfDiabeticPatientisGetsNoI() {
        String[] drugs = {"An", "P"};
        Assert.assertEquals('X', SimulatorApplication.stateAfterTreatment('D', drugs));
    }

    @Test
    public void stateAfterTreatmentIfAnyPatientGetsPAndAs() {
        String[] drugs = {"As", "P"};
        Assert.assertEquals('X', SimulatorApplication.stateAfterTreatment(anyChar(), drugs));
    }

    @Test
    public void stateAfterTreatmentIfAnyPatientGetsPAnd(){
        String[] drugs = {"As", "P"};
    }

    @Test
    public void checkNoWrongArgsIfAllArgsOk(){
        String [] args = {"D,F", "As"};
        Assert.assertTrue(SimulatorApplication.checkNoWrongArgs(args));
    }

    @Test
    public void checkNoWrongArgsIfPatientError(){
        String [] args = {"D,F,Q", "As"};
        Assert.assertFalse(SimulatorApplication.checkNoWrongArgs(args));
    }

    @Test
    public void checkNoWrongArgsIfDrufError(){
        String [] args = {"D,F", "As, Mr"};
        Assert.assertFalse(SimulatorApplication.checkNoWrongArgs(args));
    }

}