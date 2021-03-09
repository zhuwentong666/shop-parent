import com.zwt.shop.entity.BrandEntity;

/**
 * @ClassName test
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/9
 * @Version V1.0
 **/
public class test {
    public static void main(String[] args) {
        BrandEntity brandEntity = new BrandEntity();
        Class<? extends BrandEntity> aClass = brandEntity.getClass();
        System.out.println(aClass);
        ClassLoader classLoader = aClass.getClassLoader();

        System.out.println(classLoader);
    }
}
