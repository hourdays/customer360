op.fromTriples([
op.pattern(companyId, onto('boardMember'), resource('Michael_Ross')),
op.pattern(companyId, foaf('name'), companyName)
])
  .joinInner(purchases, op.on(companyName, purchases.col('company')))
  .where(cts.wordQuery('tour'))
  .joinDoc('doc',op.col('uri'))
  .select(['company', 'row_total', 'uri'])
  .groupBy('company', [op.sum(sum, 'row_total'),'uri'])
  .result();