package fitnessmanager;

public class Premium extends Family{
    public Premium(String fname, String lname, String dob, String expire, String location){
        super(fname, lname, dob, expire, location);
        this.guestPasses = 3;
    }

    @Override
    public boolean useGuestPass(){
        if(guestPasses >= 1) {
            this.guestPasses--;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnGuestPass(){
        if(guestPasses <= 2) {
            this.guestPasses++;
            return true;
        }
        return false;
    }

    /**
     * Returns String representation of this member
     * Format ex: April March, DOB: 3/31/1990, Membership expires 6/30/2023,
     *            Location: PISCATAWAY, 08854, MIDDLESEX, (Premium) Guest-pass
     *            remaining: 3
     * @return String representation of this member
     */
    @Override
    public String toString(){
        if (super.expire.compareTo(new Date()) < 0){                  //Check if membership expiration date has passed
            return super.fname + " " + super.lname + ", DOB: " + super.dob.toString()
                    +  ", Membership expired " + super.expire.toString()
                    +  ", Location: " + super.location.toString() + ", (Premium) Guest-pass remaining: "
                    +  super.guestPasses;
        }
        return super.fname + " " + super.lname + ", DOB: " + super.dob.toString()
                +  ", Membership expires " + super.expire.toString()
                +  ", Location: " + super.location.toString() + ", (Premium) Guest-pass remaining: "
                +  super.guestPasses;
    }

    @Override
    public double membershipFee(){
        return 59.99*11;
    }

}
