let $purchases-template :=
<template xmlns="http://marklogic.com/xdmp/tde">   
  <context>/order/items/item</context> 
  <rows> 
    <row> 
      <schema-name>customer360</schema-name> 
      <view-name>purchases</view-name> 
      <columns> 
        <column>
          <name>product</name> 
          <scalar-type>string</scalar-type> 
          <val>product</val> 
          <invalid-values>ignore</invalid-values> 
        </column> 
        <column>
          <name>price</name> 
          <scalar-type>decimal</scalar-type> 
          <val>price</val> 
          <invalid-values>ignore</invalid-values> 
        </column>
        <column>
          <name>quantity</name> 
          <scalar-type>integer</scalar-type> 
          <val>quantity</val> 
          <invalid-values>ignore</invalid-values> 
        </column>
      </columns>
    </row>
  </rows>
</template>

return tde:validate($purchases-template)