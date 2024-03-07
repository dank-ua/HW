package org.example.hw12;

public class Producer extends Thread {
    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        int item = 0;
        while (true){
            try {
                resource.produce(item++);
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
