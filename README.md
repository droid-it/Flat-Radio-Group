Flat-Radio-Group
===============

Flat-Radio-Group removes the need to nest RadioButtons inside a ViewGroup(RadioGroup).
To be used in conjunction with ConstraintLayout.

Usage
-----

In your gradle file, include the dependency as below:

```groovy
implementation 'com.udit.android:flatradiogroup:0.0.1'
```

Then in your xml layout, include the radio group as:

```xml
<com.udit.android.flatradiogroup.FlatRadioGroup
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:constraint_referenced_ids="radioButton1,radioButton2" />
```

License
-------

    Copyright 2018 Udit Verma

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.        
