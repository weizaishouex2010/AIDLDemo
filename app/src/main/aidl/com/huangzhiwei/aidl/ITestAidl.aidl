// ITestAidl.aidl.aidl
package com.huangzhiwei.aidl;

import com.huangzhiwei.aidl.Person;
// Declare any non-default types here with import statements

interface ITestAidl {
    //计算两个数的和
    //int add(int num1,int num2);

    List<Person> add(in Person person);
}
