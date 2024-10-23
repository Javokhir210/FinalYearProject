package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataLoader {

    public static void loadData(String filePath, BinaryTree tree) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;

            // Skip the header
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                // Parse the fields based on the CSV file format
                LocalDate reportAsOfEOD = LocalDate.parse(line[0], DateTimeFormatter.ISO_DATE);
                String loanId = line[1];
                String loanNumber = line[2];
                LocalDateTime listedOnUTC = LocalDateTime.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime biddingStartedOn = LocalDateTime.parse(line[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                Integer bidsPortfolioManager = Integer.parseInt(line[5]);
                Integer bidsApi = Integer.parseInt(line[6]);
                Integer bidsManual = Integer.parseInt(line[7]);
                String userName = line[8];
                boolean newCreditCustomer = Boolean.parseBoolean(line[9]);
                LocalDateTime loanApplicationStartedDate = LocalDateTime.parse(line[10], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDate loanDate = LocalDate.parse(line[11], DateTimeFormatter.ISO_DATE);
                LocalDate contractEndDate = LocalDate.parse(line[12], DateTimeFormatter.ISO_DATE);
                LocalDate firstPaymentDate = LocalDate.parse(line[13], DateTimeFormatter.ISO_DATE);
                LocalDate maturityDateOriginal = LocalDate.parse(line[14], DateTimeFormatter.ISO_DATE);
                LocalDate maturityDateLast = LocalDate.parse(line[15], DateTimeFormatter.ISO_DATE);
                Integer applicationSignedHour = Integer.parseInt(line[16]);
                Integer applicationSignedWeekday = Integer.parseInt(line[17]);
                Integer verificationType = Integer.parseInt(line[18]);
                Integer languageCode = Integer.parseInt(line[19]);
                Integer age = Integer.parseInt(line[20]);
                String dateOfBirth = line[21];
                Integer gender = Integer.parseInt(line[22]);
                String country = line[23];
                Double appliedAmount = Double.parseDouble(line[24]);
                Double amount = Double.parseDouble(line[25]);
                Double interest = Double.parseDouble(line[26]);
                Integer loanDuration = Integer.parseInt(line[27]);
                Double monthlyPayment = Double.parseDouble(line[28]);
                String county = line[29];
                String city = line[30];
                Integer useOfLoan = Integer.parseInt(line[31]);
                Integer education = Integer.parseInt(line[32]);
                Integer maritalStatus = Integer.parseInt(line[33]);
                Integer nrOfDependants = Integer.parseInt(line[34]);
                Integer employmentStatus = Integer.parseInt(line[35]);
                String employmentDurationCurrentEmployer = line[36];
                String employmentPosition = line[37];
                String workExperience = line[38];
                Integer occupationArea = Integer.parseInt(line[39]);
                Integer homeOwnershipType = Integer.parseInt(line[40]);
                Double incomeFromPrincipalEmployer = Double.parseDouble(line[41]);
                Double incomeFromPension = Double.parseDouble(line[42]);
                Double incomeFromFamilyAllowance = Double.parseDouble(line[43]);
                Double incomeFromSocialWelfare = Double.parseDouble(line[44]);
                Double incomeFromLeavePay = Double.parseDouble(line[45]);
                Double incomeFromChildSupport = Double.parseDouble(line[46]);
                Double incomeOther = Double.parseDouble(line[47]);
                Double incomeTotal = Double.parseDouble(line[48]);
                Integer existingLiabilities = Integer.parseInt(line[49]);
                Double liabilitiesTotal = Double.parseDouble(line[50]);
                Integer refinanceLiabilities = Integer.parseInt(line[51]);
                Double debtToIncome = Double.parseDouble(line[52]);
                Double freeCash = Double.parseDouble(line[53]);
                Integer monthlyPaymentDay = Integer.parseInt(line[54]);
                boolean activeScheduleFirstPaymentReached = Boolean.parseBoolean(line[55]);
                Double plannedPrincipalTillDate = Double.parseDouble(line[56]);
                Double plannedInterestTillDate = Double.parseDouble(line[57]);
                LocalDate lastPaymentOn = LocalDate.parse(line[58], DateTimeFormatter.ISO_DATE);
                Integer currentDebtDaysPrimary = Integer.parseInt(line[59]);
                LocalDate debtOccuredOn = LocalDate.parse(line[60], DateTimeFormatter.ISO_DATE);
                Integer currentDebtDaysSecondary = Integer.parseInt(line[61]);
                LocalDate debtOccuredOnForSecondary = LocalDate.parse(line[62], DateTimeFormatter.ISO_DATE);
                Double expectedLoss = Double.parseDouble(line[63]);
                Double lossGivenDefault = Double.parseDouble(line[64]);
                Double expectedReturn = Double.parseDouble(line[65]);
                Double probabilityOfDefault = Double.parseDouble(line[66]);
                LocalDate defaultDate = LocalDate.parse(line[67], DateTimeFormatter.ISO_DATE);
                Double principalOverdueBySchedule = Double.parseDouble(line[68]);
                Double plannedPrincipalPostDefault = Double.parseDouble(line[69]);
                Double plannedInterestPostDefault = Double.parseDouble(line[70]);
                Double ead1 = Double.parseDouble(line[71]);
                Double ead2 = Double.parseDouble(line[72]);
                Double principalRecovery = Double.parseDouble(line[73]);
                Double interestRecovery = Double.parseDouble(line[74]);
                Integer recoveryStage = Integer.parseInt(line[75]);
                LocalDate stageActiveSince = null;
                Integer modelVersion = Integer.parseInt(line[77]);
                String rating = line[78];
                Double elV0 = Double.parseDouble(line[79]);
                String ratingV0 = line[80];
                Double elV1 = Double.parseDouble(line[81]);
                String ratingV1 = line[82];
                String ratingV2 = line[83];
                String status = line[84];
                boolean restructured = Boolean.parseBoolean(line[85]);
                String activeLateCategory = line[86];
                String worseLateCategory = line[87];
                Integer creditScoreEsMicroL = Integer.parseInt(line[88]);
                Integer creditScoreEsEquifaxRisk = Integer.parseInt(line[89]);
                Integer creditScoreFiAsiakasTietoRiskGrade = Integer.parseInt(line[90]);
                Integer creditScoreEeMini = Integer.parseInt(line[91]);
                Double principalPaymentsMade = Double.parseDouble(line[92]);
                Double interestAndPenaltyPaymentsMade = Double.parseDouble(line[93]);
                Double principalWriteOffs = Double.parseDouble(line[94]);
                Double interestAndPenaltyWriteOffs = Double.parseDouble(line[95]);
                Double principalBalance = Double.parseDouble(line[96]);
                Double interestAndPenaltyBalance = Double.parseDouble(line[97]);
                Integer noOfPreviousLoansBeforeLoan = Integer.parseInt(line[98]);
                Double amountOfPreviousLoansBeforeLoan = Double.parseDouble(line[99]);
                Double previousRepaymentsBeforeLoan = Double.parseDouble(line[100]);
                Double previousEarlyRepaymentsBeforeLoan = Double.parseDouble(line[101]);
                Integer previousEarlyRepaymentsCountBeforeLoan = Integer.parseInt(line[102]);
                LocalDate gracePeriodStart = line[103].isEmpty() ? null : LocalDate.parse(line[103], DateTimeFormatter.ISO_DATE);
                LocalDate gracePeriodEnd = line[104].isEmpty() ? null : LocalDate.parse(line[104], DateTimeFormatter.ISO_DATE);
                LocalDate nextPaymentDate = line[105].isEmpty() ? null : LocalDate.parse(line[105], DateTimeFormatter.ISO_DATE);
                Integer nextPaymentNr = Integer.parseInt(line[106]);
                Integer nrOfScheduledPayments = Integer.parseInt(line[107]);
                LocalDate reScheduledOn = line[108].isEmpty() ? null : LocalDate.parse(line[108], DateTimeFormatter.ISO_DATE);
                Double principalDebtServicingCost = Double.parseDouble(line[109]);
                Double interestAndPenaltyDebtServicingCost = Double.parseDouble(line[110]);
                String activeLateLastPaymentCategory = line[111];

                // Create the DataRecord object
//                DataRecord record = new DataRecord(
//                        reportAsOfEOD, loanId, loanNumber, listedOnUTC, biddingStartedOn,
//                        bidsPortfolioManager, bidsApi, bidsManual, userName, newCreditCustomer,
//                        loanApplicationStartedDate, loanDate, contractEndDate, firstPaymentDate,
//                        maturityDateOriginal, maturityDateLast, applicationSignedHour, applicationSignedWeekday,
//                        verificationType, languageCode, age, dateOfBirth, gender, country, appliedAmount,
//                        amount, interest, loanDuration, monthlyPayment, county, city, useOfLoan,
//                        education, maritalStatus, nrOfDependants, employmentStatus, employmentDurationCurrentEmployer,
//                        employmentPosition, workExperience, occupationArea, homeOwnershipType, incomeFromPrincipalEmployer,
//                        incomeFromPension, incomeFromFamilyAllowance, incomeFromSocialWelfare, incomeFromLeavePay,
//                        incomeFromChildSupport, incomeOther, incomeTotal, existingLiabilities, liabilitiesTotal,
//                        refinanceLiabilities, debtToIncome, freeCash, monthlyPaymentDay, activeScheduleFirstPaymentReached,
//                        plannedPrincipalTillDate, plannedInterestTillDate, lastPaymentOn, currentDebtDaysPrimary,
//                        debtOccuredOn, currentDebtDaysSecondary, debtOccuredOnForSecondary, expectedLoss, lossGivenDefault,
//                        expectedReturn, probabilityOfDefault, defaultDate, principalOverdueBySchedule, plannedPrincipalPostDefault,
//                        plannedInterestPostDefault, ead1, ead2, principalRecovery, interestRecovery, recoveryStage,
//                        stageActiveSince, modelVersion, rating, elV0, ratingV0, elV1, ratingV1, ratingV2, status,
//                        restructured, activeLateCategory, worseLateCategory, creditScoreEsMicroL, creditScoreEsEquifaxRisk,
//                        creditScoreFiAsiakasTietoRiskGrade, creditScoreEeMini, principalPaymentsMade, interestAndPenaltyPaymentsMade,
//                        principalWriteOffs, interestAndPenaltyWriteOffs, principalBalance, interestAndPenaltyBalance,
//                        noOfPreviousLoansBeforeLoan, amountOfPreviousLoansBeforeLoan, previousRepaymentsBeforeLoan,
//                        previousEarlyRepaymentsBeforeLoan, previousEarlyRepaymentsCountBeforeLoan, gracePeriodStart,
//                        gracePeriodEnd, nextPaymentDate, nextPaymentNr, nrOfScheduledPayments, reScheduledOn,
//                        principalDebtServicingCost, interestAndPenaltyDebtServicingCost, activeLateLastPaymentCategory
//                );

                // Insert into the binary tree using the loanId as the key
//                tree.insert(loanId, record);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
