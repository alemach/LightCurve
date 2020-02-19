package ale.mach.lightcurve.repository;

import ale.mach.lightcurve.model.TestCurvePositive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestCurvePositiveRepository extends JpaRepository<TestCurvePositive, Integer> {

    @Query(value = "SELECT COUNT(*) FROM testcurve.test_curve_positive",nativeQuery = true)
    public Integer findRowCount();
}
