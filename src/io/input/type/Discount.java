package io.input.type;

public enum Discount {
    PERSON_OF_NATIONAL_MERIT(30, "국가유공자", 1),
    SOLDIER(20, "군인", 2),
    STUDENT(10, "학생", 3),
    NORMAL(0, "일반인", 4);

    private final int discountPercent;
    private final String name;
    private final int value;

    Discount(int discountPercent, String name, int value) {
        this.discountPercent = discountPercent;
        this.name = name;
        this.value = value;
    }

    public static Discount fromDiscountPercent(int value) {
        for (Discount discount : Discount.values()) {
            if (discount.value == value) {
                return discount;
            }
        }

        throw new IllegalArgumentException("옳바르지 않은 입력값입니다.");
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
