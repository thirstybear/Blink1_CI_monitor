package uk.co.thirstybear.blink1service.blink1.actions;

class RedirectToStdOut implements StreamListener {
    @Override
    public void notify(String value) {
        System.out.println(value);
    }
}
