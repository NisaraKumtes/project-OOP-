/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.Scanner;

class Particlecollision {
    private ArrayList<Integer> energies;
    private int totalEnergyReleased;
    
    public Particlecollision(){
        energies = new ArrayList<>();
        totalEnergyReleased = 0 ;
    }
    
    public void addEnergy(int energy){
        energies.add(energy);
    }
    public int calculaterMaxEnergyRelease(){
        while(energies.size()>1){
            int maxEnergyDiff= -1;
            int indexToRemove = -1;
            
            for(int i = 0; i< energies.size() -1; i++){
                int energyDiff = Math.abs(energies.get(i)- energies.get(i+1));
                
                if(energyDiff> maxEnergyDiff){
                    maxEnergyDiff =energyDiff;
                    indexToRemove = i;
                }else if(energyDiff == maxEnergyDiff&& indexToRemove >i){
                    indexToRemove = i;
                }
            }
            totalEnergyReleased += maxEnergyDiff;
            
            
            energies.remove(indexToRemove);
            energies.remove(indexToRemove);
        }
        
                return totalEnergyReleased;

        
    }
}
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        Particlecollision particleCollision = new Particlecollision();
        
        
        for(int i = 0; i< n; i++){
            int energy = scanner.nextInt();
            particleCollision.addEnergy(energy);
        }
        
        int totalEnergy = particleCollision.calculaterMaxEnergyRelease();
        System.out.println(totalEnergy);
        
        scanner.close();
    }
    
    
}