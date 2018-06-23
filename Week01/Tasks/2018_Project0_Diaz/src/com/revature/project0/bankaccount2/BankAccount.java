package com.revature.project0.bankaccount2;

class BankAccount{
    String name,acc_type;
    int Acc_num,Acc_Balance;
    
    BankAccount(String n,int acc_num,int b,String a_t){
            name=n;
            Acc_num=acc_num;
            Acc_Balance=b;
            acc_type=a_t;
        }
}
