package kr.plea.javakeyword;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaTest {

    private static final int N_VALUE = 10000;

    @Test
    @DisplayName("assert 키워드 사용예제")
    public void assertTest() {
        try {
            assert 1 == 1 : "hi";
        } catch (AssertionError e) {
            log.info("Catch : {}", e.getMessage());
        }
        log.info("Done");
    }

    @Test
    @DisplayName("nonSync 메서드 멀티스레드 실행")
    public void givenMultiThread_whenNonSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        SynchronizedMethods summation = new SynchronizedMethods();

        IntStream.range(0, 1000)
            .forEach(count -> service.submit(summation::calculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        // assertEquals(1000, summation.getSum());
        Assertions.assertThat(summation.getSum()).isNotEqualTo(1000);
    }

    @Test
    @DisplayName("Sync 메서드 멀티스레드 실행")
    public void givenMultiThread_whenSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        SynchronizedMethods summation = new SynchronizedMethods();

        IntStream.range(0, 1000)
            .forEach(count -> service.submit(summation::synchronizedCalculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        org.junit.jupiter.api.Assertions.assertEquals(1000, summation.getSum());
    }

    @Test
    @DisplayName("instanceOf 테스트")
    public void instanceOfTest() {
        String str1 = "123";
        int num = 0;
        if (str1 instanceof String) {
            num = Integer.parseInt(str1);
        }
        assertThat(num).isEqualTo(123);
    }

    @Test
    @DisplayName("transient 테스트")
    public void transientTest() throws IOException, ClassNotFoundException {
        //given
        Member member = new Member("플리", 17, "abc123");

        // 직렬화 실패로 오류가 난다.
        org.junit.jupiter.api.Assertions.assertThrows(NotSerializableException.class, () -> serialize(member));
    }

    @Test
    @DisplayName("finally 테스트")
    public void finallyTest() throws IOException {
        //given
        Member member = new Member("플리", 17, "abc123");

        try {
            serialize(member);
        } catch (Exception e) {
            log.info("Catch : {}", e.getMessage());
        } finally {
            log.info("Done");
            System.out.println(Double.MAX_VALUE * 10);
            System.out.println(Double.MIN_VALUE / 10);
        }
    }

    @Test
    @DisplayName("strictfp 테스트")
    //부동 소수점 변수가 작동 중일 때 모든 플랫폼에서 동일한 결과를 제공하는 것
    //다양한 CPU의 경우 표준 정밀도가 다를 수 있습니다. 32비트 정밀도는 x86 시스템의 경우 62비트 정밀도와 다릅니다.
    //strictfp를 사용하면 동일한 코드가 다른 플랫폼에서 실행될 때 정밀도가 다르기 때문에 출력이 조작되지 않도록 할 수 있으며 이는 더 높은 정밀도의 플랫폼에서 잘 작동합니다.
    public void testFloatingPointPrecision() {
        double expected = 1.006E11;
        double result = add();
        System.out.println(result);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Volatile 형태 Counter의 멀티스레드 테스트")
    //volatile은 변수를 읽거나 쓸 때에 Main Memory에서 읽고 쓴다는 뜻을 가진 키워드
    //멀티 쓰레드 환경에서 가시성 문제를 해결하고자 나왔지만 한계가 있음. Synchronized 쓰자
    public void volatileMultiThreadTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(200);
        CountDownLatch latch = new CountDownLatch(N_VALUE);

        for (int i = 0; i < N_VALUE; i++) {
            service.execute(() -> {
                CounterVolatile.increase();
                latch.countDown();
            });
        }

        latch.await();
        assertThat(CounterVolatile.idx()).isNotEqualTo(N_VALUE);
    }

    @Test
    @DisplayName("Synchronized 형태 Counter의 멀티스레드 테스트")
    public void synchronizedMultiThreadTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(200);
        CountDownLatch latch = new CountDownLatch(N_VALUE);

        for (int i = 0; i < N_VALUE; i++) {
            service.execute(() -> {
                CounterSynchronized.increase();
                latch.countDown();
            });
        }

        latch.await();
        assertThat(CounterSynchronized.idx()).isEqualTo(N_VALUE);
    }

    @Test
    @DisplayName("AtomicInteger 형태 Counter의 멀티스레드 테스트")
    // Atomic 클래스는 CAS(Compare And Swap)을 이용
    // CAS(Compare And Swap)은 현재 쓰레드에 저장된 값과 Main Memory에 저장된 값을 비교해서
    // 일치하는 경우 새로운 값으로 교체해주고, 일치 하지 않는다면 실패 후 다시 재시도를 하는 알고리즘
    public void AtomicIntegerMultiThreadTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(200);
        CountDownLatch latch = new CountDownLatch(N_VALUE);

        for (int i = 0; i < N_VALUE; i++) {
            service.execute(() -> {
                CounterAtomic.increase();
                latch.countDown();
            });
        }

        latch.await();
        assertThat(CounterAtomic.idx()).isEqualTo(N_VALUE);
    }

    public strictfp double add() {
        double number1 = 10e+10;
        double number2 = 6e+08;
        return number1 + number2;
    }

    private byte[] serialize(Member member) throws IOException {
        byte[] serializedMember;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(member);
            serializedMember = byteArrayOutputStream.toByteArray();
        }
        return serializedMember;
    }

    // private Member deSerialize(byte[] serializedMember) throws IOException, ClassNotFoundException {
    // 	Member member;
    // 	try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedMember);
    // 		 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
    // 		// 역직렬화된 Member 객체를 읽어온다.
    // 		Object objectMember = objectInputStream.readObject();
    // 		member = (Member) objectMember;
    // 	}
    // 	return member;
    // }

    @Getter
    @Setter
    private class SynchronizedMethods {

        private int sum = 0;

        public void calculate() {
            setSum(getSum() + 1);
        }

        public synchronized void synchronizedCalculate() {
            setSum(getSum() + 1);
        }
    }

    public class CounterSynchronized {

        private static int idx = 0;

        public static synchronized int increase() {
            return idx++;
        }

        public static int idx() {
            return idx;
        }
    }

    public class CounterVolatile {

        private static volatile int idx = 0;

        public static int increase() {
            return idx++;
        }

        public static int idx() {
            return idx;
        }
    }

    public class CounterAtomic {
        private static AtomicInteger idx = new AtomicInteger(0);

        public static int increase() {
            return CounterAtomic.idx.getAndIncrement();
        }

        public static int idx() {
            return idx.get();
        }
    }

}
