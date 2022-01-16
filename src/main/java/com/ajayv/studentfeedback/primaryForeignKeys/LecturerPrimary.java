package com.ajayv.studentfeedback.primaryForeignKeys;

import com.ajayv.studentfeedback.objects.Lecturer;

import java.io.Serializable;
import java.util.Objects;

public class LecturerPrimary implements Serializable {
    private Lecturer lecturer;

    public LecturerPrimary(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LecturerPrimary)) return false;
        LecturerPrimary that = (LecturerPrimary) o;
        return lecturer.equals(that.lecturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturer);
    }
}
