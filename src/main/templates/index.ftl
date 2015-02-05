<#import "layout.ftl" as layout>
<@layout.mainLayout>

<div class="row form-group dateFilter">
    <form class="form-inline">
        <#if startDateHasError || endDateHasError >
            <div class="alert alert-info" role="alert">
                <ul>
                    <#if startDateHasError><li>De startdatum is illegaal</li></#if>
                    <#if endDateHasError><li>De einddatum is illegaal</li></#if>
                </ul>
                Waarde(n) genegeerd.
            </div>
        </#if>
        <div class="form-group <#if startDateHasError>has-warning</#if>">
            <label class="sr-only" for="startDate">Van</label>
            <input type="text" name="startDate" id="startDate" value="${startDate}" placeholder="Vanaf (dd-mm-jjjj)" class="form-control">
        </div>
        <div class="form-group <#if endDateHasError>has-warning</#if>">
            <label class="sr-only" for="endDate">Tot</label>
            <input type="text" name="endDate" id="endDate" value="${endDate}" placeholder="t/m (dd-mm-jjjj)" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Filter</button>
    </form>
</div>


<div class="row">
    <div class="col-xs-3 col-sm-4 overzicht">
        <h2>Overzicht</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Resultaat</th>
                <th>Aantal</th>
            </tr>
            </thead>
            <tbody>
                <#list stats.resultaatBreakdown as item>
                <tr>
                    <td>${item.label}</td>
                    <td>${item.value?c}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <div class="col-xs-3 col-sm-4 geslaagd">
        <h2>Per sector</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Transitie</th>
                <th>Ratio</th>
                <th><span class="glyphicon glyphicon-thumbs-up"></span></th>
                <th><span class="glyphicon glyphicon-thumbs-down"></span></th>
            </tr>
            </thead>
            <#list stats.sectorBreakdown as item>
                <tr>
                    <td>${item.transitie}</td>
                    <td>${item.ratio}</td>
                    <td>${item.geslaagd?c}</td>
                    <td>${item.nietGeslaagd?c}</td>
                </tr>
            </#list>
        </table>

    </div>
    <div class="col-xs-3 col-sm-4 schoolFalen">
        <h2>Fouten per school</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>BRIN</th>
                <th>Aanvrager</th>
                <th>Verstrekker</th>
            </tr>
            </thead>
            <tbody>
            <#list stats.schoolFalen as item>
                <tr>
                    <td><a href="/statistieken/school?brin=${item.brin}">${item.brin}</a></td>
                    <td>${item.aanvrager?c}</td>
                    <td>${item.verstrekker?c}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
                $("div.schoolFalen table").tablesorter();
            }
    );
</script>
</@layout.mainLayout>
