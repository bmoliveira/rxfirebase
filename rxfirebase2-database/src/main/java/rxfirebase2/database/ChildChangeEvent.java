package rxfirebase2.database;

import com.google.firebase.database.DataSnapshot;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public final class ChildChangeEvent extends ChildEvent {

    private final String previousChildName;

    @CheckReturnValue
    @NonNull
    public static ChildChangeEvent create(DataSnapshot dataSnapshot, String previousChildName) {
        return new ChildChangeEvent(dataSnapshot, previousChildName);
    }

    private ChildChangeEvent(DataSnapshot dataSnapshot, String previousChildName) {
        super(dataSnapshot);
        this.previousChildName = previousChildName;
    }

    @Nullable
    public String previousChildName() {
        return previousChildName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ChildChangeEvent that = (ChildChangeEvent) o;

        return previousChildName != null ? previousChildName.equals(that.previousChildName)
                : that.previousChildName == null;

    }

    @Override
    public int hashCode() {
        int result = dataSnapshot.hashCode();
        result = 31 * result + (previousChildName != null ? previousChildName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChildChangeEvent{"
                + "dataSnapshot=" + dataSnapshot
                + ", previousChildName='" + previousChildName + '\''
                + '}';
    }

}
