package com.lev.medication;

import java.util.HashMap;

public class MedicationServiceMapImpl implements MedicationService {
    private HashMap<Integer, Medication> medicationMap;

    public MedicationServiceMapImpl() {
        medicationMap = new HashMap<>();
    }

    public void addMedicine(Medication medication) {
        medicationMap.put(medication.getId(), medication);
    }

    // in this method i have assumed that "the total number of medications that have been inputted" refers to total
    // amount of medicine strings that have been posted (i.e. total amount of Medicine objects created), not the total
    // amount of unique medicationIds.
    @Override
    public HashMap<String, Integer> getStatistics() {
        HashMap<String, Integer> statistics = new HashMap<String, Integer>();
        
        int totalMeds = getTotalMeds(medicationMap);
        int totalDosages = getTotalDosages(medicationMap);
        HashMap<String, Integer> totalMedsPerBottleSize = getTotalMedsPerBottleSize(medicationMap);
        HashMap<String, Integer> supplyCountPerMed = getSupplyCountPerMed(medicationMap);
        
        statistics.put("totalMeds", totalMeds);
        statistics.put("totalDosages", totalDosages);
        for (String bottleSize : totalMedsPerBottleSize.keySet()) {
            statistics.put(String.format("Total medication in bottle size %s:", bottleSize), totalMedsPerBottleSize.get(bottleSize));
        }
        for (String medicationId : supplyCountPerMed.keySet()) {
            statistics.put(String.format("Total times supplied with medicationID %s:", medicationId), supplyCountPerMed.get(medicationId));
        }

        return statistics;
    }

    private int getTotalMeds(HashMap<Integer, Medication> medicationMap) {
        int totalMeds = 0;

        for (int i = 0; i < medicationMap.size(); i++) {
            totalMeds += 1;
        }

        return totalMeds;
    }

    private int getTotalDosages(HashMap<Integer, Medication> medicationMap) {
        int totalDosages = 0;

        for (Medication medication : medicationMap.values()) {
            totalDosages += medication.getDosageCount();
        }

        return totalDosages;
    }

    private HashMap<String, Integer> getTotalMedsPerBottleSize(HashMap<Integer, Medication> medicationMap) {
        HashMap<String, Integer> totalMedsPerBottleSize = new HashMap<String, Integer>();

        for (Medication medication : medicationMap.values()) {
            String bottleSize = medication.getBottleSize();
            totalMedsPerBottleSize.merge(bottleSize, 1, Integer::sum);
        }

        return totalMedsPerBottleSize;
    }

    private HashMap<String, Integer> getSupplyCountPerMed(HashMap<Integer, Medication> medicationMap) {
        HashMap<String, Integer> supplyCountPerMed = new HashMap<String, Integer>();

        for (Medication medication : medicationMap.values()) {
            String medicationId = medication.getMedicationId();
            supplyCountPerMed.merge(medicationId, 1, Integer::sum);
        }

        return supplyCountPerMed;
    }
}
