package com.androidhuman.rxfirebase.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import com.memoizrlabs.retrooptional.Optional;

import rx.Observable;
import rx.Subscriber;

final class DataOnSubscribe implements Observable.OnSubscribe<Optional<DataSnapshot>> {

    private final DatabaseReference ref;

    DataOnSubscribe(DatabaseReference ref) {
        this.ref = ref;
    }

    @Override
    public void call(final Subscriber<? super Optional<DataSnapshot>> subscriber) {
        final ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(Optional.of(dataSnapshot));
                    subscriber.onCompleted();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(databaseError.toException());
                }
            }
        };

        ref.addListenerForSingleValueEvent(listener);
    }
}