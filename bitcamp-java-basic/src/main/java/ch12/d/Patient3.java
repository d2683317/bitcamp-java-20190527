package ch12.d;

public class Patient3 {
  public final static int WOMAN = 1;
  public final static int MAN = 2;

  private String name;
  private int age;
  private int height;
  private int weight;
  private int gender;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHeight() {
    return this.height;
  }

  public void setHeight(int height) {
    if (height > 0 && height < 300)
      this.height = height;
    else
      throw new RuntimeException("키가 유효하지 않습니다!");
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int weight) {
    if (weight > 0 && weight < 500)
      this.weight = weight;
    else
      throw new RuntimeException("몸무게가 유효하지 않습니다!");
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    if (age > 0 && age < 150)
      this.age = age;
    else
      throw new RuntimeException("나이가 유효하지 않습니다!");
  }
  
  public int getGender() {
    return gender;
  }
  
  public void setGender(int gender) {
    if(gender > 0 && gender < 3)
      this.gender = gender;
    else
      throw new RuntimeException("성별이 유효하지 않습니다!");
  }

  public String toString() {
    return String.format("name=%s, age=%d, height=%d, weight=%d, gender=%d", this.name, this.age,
        this.height, this.weight, this.gender);
    // String s = "name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight
    // + ", gender=" + gender;
  }
}
