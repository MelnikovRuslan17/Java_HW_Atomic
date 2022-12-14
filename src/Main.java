import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger nickLengthThree = new AtomicInteger(0);
    public static AtomicInteger nickLengthFour = new AtomicInteger(0);
    public static AtomicInteger nickLengthFive = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));

        }
        Thread thread1 = new Thread(() -> {
            for (String nickName : texts) {
                if (palindrome(nickName)) {
                    switch (nickName.length()) {
                        case 3:
                            nickLengthThree.incrementAndGet();
                            break;
                        case 4:
                            nickLengthFour.incrementAndGet();
                            break;
                        case 5:
                            nickLengthFive.incrementAndGet();
                            break;
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (String nickName : texts) {
                if (oneChar(nickName)) {
                    switch (nickName.length()) {
                        case 3:
                            nickLengthThree.incrementAndGet();
                            break;
                        case 4:
                            nickLengthFour.incrementAndGet();
                            break;
                        case 5:
                            nickLengthFive.incrementAndGet();
                            break;

                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (String nickName : texts) {
                if (ascending(nickName)) {
                    switch (nickName.length()) {
                        case 3:
                            nickLengthThree.incrementAndGet();
                            break;
                        case 4:
                            nickLengthFour.incrementAndGet();
                            break;
                        case 5:
                            nickLengthFive.incrementAndGet();
                            break;
                    }

                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("???????????????? ???????? c ???????????? 3: " + nickLengthThree.get() + " ????");
        System.out.println("???????????????? ???????? c ???????????? 4: " + nickLengthFour.get() + " ????");
        System.out.println("???????????????? ???????? c ???????????? 5: " + nickLengthFive.get() + " ????");

    }

    public static boolean palindrome(String nickName) {
        return nickName.equalsIgnoreCase(new StringBuilder(nickName).reverse().toString());
    }

    public static boolean oneChar(String nickName) {
        for (int i = 0; i < nickName.length() - 1; i++) {
            if (nickName.charAt(i) != nickName.charAt(i + 1)) {
                return false;

            }

        }
        return true;
    }
    public static boolean ascending(String nickName) {
        for (int i = 0; i < nickName.length() - 1; i++) {
            if (nickName.charAt(i) > nickName.charAt(i + 1)) {
                return false;
            }

        }
        return true;
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();

    }
}