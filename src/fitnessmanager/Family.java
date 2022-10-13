package fitnessmanager;

public class Family extends Member{
    protected int guestPasses;

    public Family(String fname, String lname, String dob, String expire, String location){
        super(fname, lname, dob, expire, location);
        this.guestPasses = 1;
    }

    public int getGuestPasses(){
        return this.guestPasses;
    }

    public boolean useGuestPass() {
        if(guestPasses == 1) {
            this.guestPasses--;
            return true;
        }
        return false;
    }

    public boolean returnGuestPass() {
        if(guestPasses == 0) {
            this.guestPasses++;
            return true;
        }
        return false;
    }

    /**
     * Returns String representation of this member
     * Format ex: April March, DOB: 3/31/1990, Membership expires 6/30/2023,
     *            Location: PISCATAWAY, 08854, MIDDLESEX, (Family) Guest-pass
     *            remaining: 1
     * @return String representation of this member
     */
    @Override
    public String toString(){
        if (super.expire.compareTo(new Date()) < 0){                  //Check if membership expiration date has passed
            return super.fname + " " + super.lname + ", DOB: " + super.dob.toString()
                    +  ", Membership expired " + super.expire.toString()
                    +  ", Location: " + super.location.toString() + ", (Family) Guest-pass remaining: "
                    +  this.guestPasses;
        }
        return super.fname + " " + super.lname + ", DOB: " + super.dob.toString()
                +  ", Membership expires " + super.expire.toString()
                +  ", Location: " + super.location.toString() + ", (Family) Guest-pass remaining: "
                +  this.guestPasses;
    }

    @Override
    public double membershipFee(){
        return ((3 * 59.99) + 29.99);
    }
}