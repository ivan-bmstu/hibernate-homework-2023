package ru.hh.school.service;

import ru.hh.school.util.TransactionHelper;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.GenericDao;
import ru.hh.school.entity.Employer;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EmployerService {
  private final GenericDao genericDao;
  private final EmployerDao employerDao;
  private final TransactionHelper transactionHelper;

  public EmployerService(GenericDao genericDao, EmployerDao employerDao, TransactionHelper transactionHelper) {
    this.genericDao = genericDao;
    this.employerDao = employerDao;
    this.transactionHelper = transactionHelper;
  }

  public String getNameById(int employerId) {
    return transactionHelper.inTransaction(() -> genericDao.get(Employer.class, employerId).getCompanyName());
  }

  public Employer getById(int employerId) {
    return transactionHelper.inTransaction(() -> genericDao.get(Employer.class, employerId));
  }

  public Employer getByIdPrefetched(int employerId) {
    return transactionHelper.inTransaction(() -> employerDao.getEager(employerId));
  }

  public void block(int employerId) {
    transactionHelper.inTransaction(() -> {
      Employer employer = employerDao.getEager(employerId);
      employer.setBlockTime(LocalDateTime.now());
      employer.getVacancies().forEach(v -> v.setArchivingTime(LocalDateTime.now()));
    });
  }

  public void blockIfEmployerUseBadWords(int employerId) {
    Employer employer = transactionHelper.inTransaction(() -> employerDao.getEager(employerId));

    if (!checkIfWordIsBad(employer.getCompanyName())) {
      return;
    }

    if (employer.getVacancies().stream().noneMatch(v -> checkIfWordIsBad(v.getTitle()))) {
      return;
    }

    employer.setBlockTime(LocalDateTime.now());
    employer.getVacancies().forEach(v -> v.setArchivingTime(LocalDateTime.now()));
    transactionHelper.inTransaction(() -> {
      genericDao.update(employer);
    });
  }

  // долгая и важная логика, которую нельзя делать в транзакции
  private boolean checkIfWordIsBad(String companyNameToIgnore) {
    CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      return true;
    });

    try {
      return future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return true;
  }


}
