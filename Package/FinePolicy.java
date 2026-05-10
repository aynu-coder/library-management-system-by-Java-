
package Package;

public interface FinePolicy {
    double FINE_PER_DAY = 10;
    
    double calculateFine(int lateDays);
}
