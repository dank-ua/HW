package org.example.hw12;

public class Testfizz extends Thread{
    private Resource resource;

    public Testfizz(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {

            try {
                resource.trzz();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
