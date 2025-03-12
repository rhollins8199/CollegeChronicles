/*
 * ItemsManager Class: Manages item behaviors. 
 */

 package Model.Managers;

 import Model.Item;
 
 import java.util.*;
 import java.util.concurrent.Executors;
 import java.util.concurrent.ScheduledExecutorService;
 import java.util.concurrent.TimeUnit;
 
 public class ItemsManager {
 
     // Dependencies
     private final List<Item> items;
     private static final int SCANTRON_COUNT = 9; // Number of scantrons to place
     private ScheduledExecutorService scheduler; // Scheduled task to randomly place scantrons
 
     // Constructor
     public ItemsManager(List<Item> items) {
         this.items = items;
     }
 
     // To Access Getters
     public List<Item> getItems() {
         return items;
     }
 
     // Randomly place scantrons in the items list
     public void randomlyPlaceScantrons() {
         for (Item item : items) {
             item.setItemCount(0);
         }
 
         List<Item> shuffledItems = new ArrayList<>(items);
         Collections.shuffle(shuffledItems);
 
         int remainingScantrons = SCANTRON_COUNT;
 
         for (Item item : shuffledItems) {
             if (remainingScantrons <= 0) {
                 break; 
             }
 
             int scantronsToPlace = (remainingScantrons > 1 && Math.random() < 0.5) ? 2 : 1;
             item.setItemCount(scantronsToPlace);
             remainingScantrons -= scantronsToPlace;
         }
 
     }
 
     // Start and stop the scheduled task to randomly place scantrons
     public void startRandomPlacementTask() {
         if (scheduler == null || scheduler.isShutdown()) {
             scheduler = Executors.newSingleThreadScheduledExecutor();
             scheduler.scheduleAtFixedRate(this::randomlyPlaceScantrons, 0, 2, TimeUnit.MINUTES);
         }
     }
 
     // Stop the scheduled task to randomly place scantrons
     public void stopRandomPlacementTask() {
         if (scheduler != null && !scheduler.isShutdown()) {
             scheduler.shutdown();
             try {
                 if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                     scheduler.shutdownNow();
                 }
             } catch (InterruptedException e) {
                 scheduler.shutdownNow();
             }
         }
     }
 
 }
 