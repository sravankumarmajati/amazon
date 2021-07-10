package ac.app.dao.impl;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.transaction.Transaction;
import com.google.gson.Gson;
import ac.app.contract.student.logic.entity.Student;
import ac.app.contract.student.logic.entity.StudentResult;
import ac.app.dao.StudentContractDao;
import ac.app.repository.ContractRepository;
import cn.hyperchain.contract.BaseInvoke;

import java.util.List;

import ac.app.contract.student.invoke.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentContractDaoImpl implements StudentContractDao {

    Logger logger = Logger.getLogger(StudentContractDaoImpl.class);
    final ContractRepository repository;

    Account account;
    @Autowired public StudentContractDaoImpl(ContractRepository repository) {
        this.repository = repository;
        String accountJson = repository.queryAccountJson("default");
        String accountJsonPwd = repository.queryAccountJsonPwd("default");
        try {
            this.account = new Account(accountJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override public boolean registerStudent(List<Student> students) {
        // decrypt account
        String contractAddress = repository.queryAddress(repository.queryContractName(), true);
        try {
            InvokeRegisterStudent invokeRegisterStudent = new InvokeRegisterStudent(students);
            return newTx(contractAddress, invokeRegisterStudent, Boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override public boolean changeStudent(Student student) {
        String contractAddress = repository.queryAddress(repository.queryContractName(), true);
        try {
            InvokeChangeStudent invokeChangeStudent = new InvokeChangeStudent(student);
            return newTx(contractAddress, invokeChangeStudent, Boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override public Student getStudent(String id) {
        String contractAddress = repository.queryAddress(repository.queryContractName(), true);
        try {
            InvokeGetStudent invokeGetStudent = new InvokeGetStudent(id);
            return newTx(contractAddress, invokeGetStudent, Student.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public List<Student> getStudents(List<String> ids) {
        String contractAddress = repository.queryAddress(repository.queryContractName(), true);
        try {
            InvokeGetStudents invokeGetStudents = new InvokeGetStudents(ids);
            // TODO check list generic type
            return newTx(contractAddress, invokeGetStudents, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public StudentResult isContains(Student student) {
        String contractAddress = repository.queryAddress(repository.queryContractName(), true);
        try {
            InvokeIsContains invokeIsContains = new InvokeIsContains(student);
            return newTx(contractAddress, invokeIsContains, StudentResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private <T> T newTx(String contractAddress, BaseInvoke invokeBench01, Class<T> klass) throws Exception {
        Transaction invokeTx = new Transaction.HVMBuilder(account.getAddress()).invoke(contractAddress, invokeBench01).build();
        String ret = repository.invoke(invokeTx);
        return new Gson().fromJson(ret, klass);
    }
}
