`**重载和重写的区别**`
重载：发生在一个类里面，方法名必须相同，参数类型、个数、顺序不同，方法返回值和访问修饰符可以不同
重写：发生在父子类中，
    方法名和参数类型个数必须相同，
    返回值范围必须小于等于父类，
    抛出异常范围必须小于等于父类，
    访问修饰符必须大于等于父类，
    因此如果父类方法用private修饰则不能重写
    
    