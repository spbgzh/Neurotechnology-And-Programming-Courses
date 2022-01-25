package com.epam.rd.autocode.service;

public class Paging {
    public final int page;
    public final int itemPerPage;


    public Paging(final int page, final int itemPerPage) {
        this.page = page;
        this.itemPerPage = itemPerPage;
    }

    public static PagingPart page(final int page) {
        return new PagingPart(page);
    }

    public static class PagingPart {
        private final int page;

        private PagingPart(final int page) {
            this.page = page;
        }

        public Paging per(final int itemPerPage) {
            return new Paging(page, itemPerPage);
        }

    }
}
