'use strict';

const companyName = 'Campus Bikes';

const coSearchResult = cts.search(cts.andQuery([cts.elementWordQuery(xs.QName('co'),companyName), cts.directoryQuery('/marketing/')]));
const companySearchResult = cts.search(cts.andQuery([cts.elementWordQuery(xs.QName('company'),companyName), cts.directoryQuery('/marketing/')]));
const names = [];

for (const item of coSearchResult) {
  names.push(`${item.xpath('/contact/name/first/text()')} ${item.xpath('/contact/name/last/text()')}`);
};

for (const item of companySearchResult) {
  names.push(`${item.xpath('/contact/first-name/text()')} ${item.xpath('/contact/last-name/text()')}`);
};

names;
